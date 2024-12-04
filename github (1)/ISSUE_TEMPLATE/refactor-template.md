---
name: 🔄 Refactoring
about: 리팩터링할 부분이 있나요?
title: "리팩터링할 간략한 내용"
labels: 'refactor'
assignees: ''
---

## 📝 리팩터링 설명
<!-- 리팩터링이 필요한 이유와 리팩터링할 부분을 설명해 주세요. -->
<!-- 예: 로그인 로직이 복잡하여 가독성이 떨어지고, 유지보수가 어렵습니다. -->

> Controller에서 ResponseEntity를 반환하지 않아 응답 처리 일관성이 떨어짐
> 데이터가 없을 때 적절한 상태 코드 반환 로직 부족


## 🎯 리팩터링 목표
<!-- 리팩터링을 통해 달성하고자 하는 목표를 설명해 주세요. -->
<!-- 예: 코드 가독성 향상 및 중복 로직 제거 -->

> ResponseEntity를 통해 일관된 응답 제공
> 빈 데이터나 잘못된 요청에 대한 상태 코드 반환


## 🔄 리팩터링 방법
<!-- 리팩터링을 수행할 방식과 계획을 설명해 주세요. -->
<!-- 예: 로그인 로직을 별도 클래스로 분리하고, 중복된 유효성 검사를 함수로 정리 -->

> Controller 메서드에서 ResponseEntity로 return하도록 수정
> null이나 빈 데이터에 대해 204 또는 404 상태 코드 반환 로직 추가


## 📄 추가 정보 (선택 사항)
<!-- 리팩터링과 관련된 기타 추가 정보를 제공해 주세요. -->
<!-- 예: 코드 예시, 관련 링크 등 -->
