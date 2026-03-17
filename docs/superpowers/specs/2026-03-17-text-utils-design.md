# Text Processing Utilities — Design Spec

**Date:** 2026-03-17
**Goal:** Superpowers 플러그인 전체 사이클(brainstorming → writing-plans → TDD → code-review) 경험을 위한 간단한 실습 프로젝트

---

## 목적

이 프로젝트는 superpowers 플러그인의 brainstorming → writing-plans → TDD → code-review 워크플로우 사이클을 처음부터 끝까지 경험하는 것이 목적입니다. 구현 자체보다 **사이클 흐름 습득**에 초점을 둡니다.

---

## 기술 스택

- **언어:** Java 17+
- **빌드 도구:** Maven 또는 Gradle (선택)
- **테스트 프레임워크:** JUnit 5

---

## 범위

`TextUtils.java` 클래스에 정적 메서드 3개를 구현합니다.
테스트는 `TextUtilsTest.java`에 작성합니다.

---

## 메서드 스펙

### `truncate(String text, int maxLen) -> String`

긴 텍스트를 지정한 최대 길이로 자르고 말줄임표(`...`)를 붙입니다.

- `text.length() <= maxLen`이면 원본 그대로 반환
- `text.length() > maxLen`이면 `text.substring(0, maxLen - 3) + "..."` 반환
- `maxLen <= 3`이면 `"..."` 반환
- `text`가 빈 문자열이면 빈 문자열 반환
- `text`가 `null`이면 빈 문자열 반환

### `slugify(String text) -> String`

문자열을 URL 친화적인 슬러그로 변환합니다.

- 소문자 변환
- 앞뒤 공백 제거
- 연속 공백을 단일 `-`로 변환
- 영문자, 숫자, `-` 외 문자 제거 (한글 등 비ASCII 포함)
- 결과 앞뒤 `-` 제거
- `text`가 빈 문자열 또는 `null`이면 빈 문자열 반환

### `countWords(String text) -> int`

공백 기준으로 단어 수를 셉니다.

- 다중 공백은 하나의 구분자로 처리
- 앞뒤 공백 무시
- 빈 문자열 또는 `null`이면 `0` 반환

---

## 파일 구조

```
text-utils/
├── pom.xml (또는 build.gradle)
└── src/
    ├── main/java/
    │   └── TextUtils.java
    └── test/java/
        └── TextUtilsTest.java
```

---

## superpowers 사이클

```
brainstorming (완료)
  → writing-plans   : 구현 계획 및 태스크 목록 작성
    → TDD           : 테스트 먼저 작성 → 구현 → 통과
      → code-review : 완성된 코드 품질 검토
```

---

## 제약 사항

- 외부 라이브러리 사용 금지 (Java 표준 라이브러리 + JUnit 5만)
- 모든 메서드는 `static`
- `null` 입력 방어 처리 필수
