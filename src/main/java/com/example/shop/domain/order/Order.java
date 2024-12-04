package com.example.shop.domain.order;

import com.example.shop.domain.BaseEntity;
import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.user.User;
import com.example.shop.global.exception.EmptyCartException;
import com.example.shop.global.exception.EmptyOrderDetailException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name="total_price")
    @NotNull
    private BigDecimal totalPrice;

    @Valid
    @Embedded
    private DeliveryInfo deliveryInfo;

    @Column(name = "order_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder(builderMethodName = "createOrder")  // createOrder() 메서드로 builder 호출
    public Order(User user, DeliveryInfo deliveryInfo) {
        this.orderStatus = OrderStatus.PAID;  // 초기 상태 설정
        this.deliveryInfo = deliveryInfo;
        addUser(user);
    }

    // 연관관계 편의 메서드 - User 관련
    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    // 연관관계 편의 메서드 - OrderDetail 관련
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    // CartDetail -> OrderDetail 변환 및 추가를 위한 비즈니스 메서드
    public void createOrderDetailsFromCart(List<CartDetail> cartDetails) {
        if (cartDetails == null || cartDetails.isEmpty()) {
            throw new EmptyCartException();
        }

        // CartDetail -> OrderDetail 변환
        cartDetails.forEach(cartDetail -> {
            OrderDetail orderDetail = OrderDetail.createOrderDetail(
                    cartDetail.getProduct(),
                    cartDetail.getQuantity()
            );
            addOrderDetail(orderDetail);
        });

        this.totalPrice = calculateTotalPrice();
    }

    // 주문 생성 후 상세 내역에 있는 물품들의 총 금액 계산
    public BigDecimal calculateTotalPrice() {
        if (orderDetails == null || orderDetails.isEmpty()) {
            throw new EmptyOrderDetailException();
        }

        return orderDetails.stream()
                .map(orderDetail -> BigDecimal.valueOf(orderDetail.getQuantity()).multiply(orderDetail.getProduct().getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 주문 상태 변경
    public void changeStatus(OrderStatus status) {
        this.orderStatus = status;
    }
}
