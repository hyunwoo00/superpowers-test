# Text Utils Implementation Plan

> **For agentic workers:** REQUIRED: Use superpowers:subagent-driven-development (if subagents available) or superpowers:executing-plans to implement this plan. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Java로 `truncate`, `slugify`, `countWords` 세 가지 순수 텍스트 처리 유틸리티 메서드를 TDD로 구현한다.

**Architecture:** `TextUtils` 클래스에 정적 메서드 3개를 구현한다. 테스트는 JUnit 5로 작성하며, 각 메서드는 독립적으로 테스트 가능하다.

**Tech Stack:** Java 17+, Maven, JUnit 5 (jupiter)

---

## 파일 구조

```
text-utils/
├── pom.xml
└── src/
    ├── main/java/
    │   └── TextUtils.java       # 구현 (신규)
    └── test/java/
        └── TextUtilsTest.java   # JUnit 5 테스트 (신규)
```

---

### Task 1: 프로젝트 초기화

**Files:**
- Create: `text-utils/pom.xml`
- Create: `text-utils/src/main/java/TextUtils.java` (빈 클래스)
- Create: `text-utils/src/test/java/TextUtilsTest.java` (빈 테스트 클래스)

- [ ] **Step 1: `text-utils/` 디렉토리 생성**

```bash
mkdir -p text-utils/src/main/java
mkdir -p text-utils/src/test/java
```

- [ ] **Step 2: `pom.xml` 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>text-utils</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

- [ ] **Step 3: 빈 `TextUtils.java` 작성**

`text-utils/src/main/java/TextUtils.java`:
```java
public class TextUtils {
}
```

- [ ] **Step 4: 빈 `TextUtilsTest.java` 작성**

`text-utils/src/test/java/TextUtilsTest.java`:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextUtilsTest {
}
```

- [ ] **Step 5: 빌드가 되는지 확인**

```bash
cd text-utils && mvn compile test
```
Expected: `BUILD SUCCESS` (테스트 없음)

- [ ] **Step 6: Commit**

```bash
git add text-utils/
git commit -m "chore: initialize text-utils Java project"
```

---

## 브랜치 전략

- `main` — 안정 브랜치 (직접 커밋 금지)
- `feature/task-N-이름` — 태스크별 작업 브랜치
- 각 태스크 완료 후 GitHub PR 생성 → merge → 다음 태스크

```
main
 └─ feature/task-2-truncate   → PR → merge
 └─ feature/task-3-slugify    → PR → merge
 └─ feature/task-4-count-words → PR → merge
```

---

### Task 2: `truncate` 구현 (TDD)

**Files:**
- Modify: `src/test/java/TextUtilsTest.java`
- Modify: `src/main/java/TextUtils.java`

- [ ] **Step 0: feature 브랜치 생성**

```bash
git checkout -b feature/task-2-truncate
```

- [ ] **Step 1: 실패하는 테스트 작성**

`TextUtilsTest.java`에 추가:
```java
@Test
void truncate_shortText_returnsOriginal() {
    assertEquals("hello", TextUtils.truncate("hello", 10));
}

@Test
void truncate_longText_returnsTruncatedWithEllipsis() {
    assertEquals("hel...", TextUtils.truncate("hello world", 6));
}

@Test
void truncate_maxLenThreeOrLess_returnsEllipsis() {
    assertEquals("...", TextUtils.truncate("hello", 3));
    assertEquals("...", TextUtils.truncate("hello", 1));
}

@Test
void truncate_emptyOrNull_returnsEmpty() {
    assertEquals("", TextUtils.truncate("", 5));
    assertEquals("", TextUtils.truncate(null, 5));
}
```

- [ ] **Step 2: 테스트 실행해서 실패 확인**

```bash
cd text-utils && mvn test
```
Expected: FAIL — `cannot find symbol: method truncate`

- [ ] **Step 3: 최소 구현 작성**

`TextUtils.java`에 추가:
```java
public static String truncate(String text, int maxLen) {
    if (text == null || text.isEmpty()) return "";
    if (text.length() <= maxLen) return text;
    if (maxLen <= 3) return "...";
    return text.substring(0, maxLen - 3) + "...";
}
```

- [ ] **Step 4: 테스트 통과 확인**

```bash
cd text-utils && mvn test
```
Expected: `BUILD SUCCESS`, 4 tests passed

- [ ] **Step 5: Commit**

```bash
git add src/
git commit -m "feat: implement truncate with TDD"
```

- [ ] **Step 6: PR 생성 및 merge**

```bash
git push -u origin feature/task-2-truncate
```

GitHub에서 PR 생성 → main으로 merge → 로컬 main 업데이트:

```bash
git checkout main && git pull
```

---

### Task 3: `slugify` 구현 (TDD)

**Files:**
- Modify: `src/test/java/TextUtilsTest.java`
- Modify: `src/main/java/TextUtils.java`

- [ ] **Step 0: feature 브랜치 생성**

```bash
git checkout -b feature/task-3-slugify
```

- [ ] **Step 1: 실패하는 테스트 작성**

`TextUtilsTest.java`에 추가:
```java
@Test
void slugify_basic_convertsToLowerKebab() {
    assertEquals("hello-world", TextUtils.slugify("Hello World"));
}

@Test
void slugify_multipleSpaces_collapsedToSingleDash() {
    assertEquals("hello-world", TextUtils.slugify("hello   world"));
}

@Test
void slugify_specialChars_removed() {
    assertEquals("hello-world", TextUtils.slugify("hello! world?"));
}

@Test
void slugify_korean_removed() {
    assertEquals("hello", TextUtils.slugify("hello 한글"));
}

@Test
void slugify_emptyOrNull_returnsEmpty() {
    assertEquals("", TextUtils.slugify(""));
    assertEquals("", TextUtils.slugify(null));
}

@Test
void slugify_leadingTrailingDashes_stripped() {
    assertEquals("hello", TextUtils.slugify("  hello  "));
}
```

- [ ] **Step 2: 테스트 실행해서 실패 확인**

```bash
cd text-utils && mvn test
```
Expected: FAIL — `cannot find symbol: method slugify`

- [ ] **Step 3: 최소 구현 작성**

`TextUtils.java`에 추가:
```java
public static String slugify(String text) {
    if (text == null || text.isEmpty()) return "";
    return text.trim()
               .toLowerCase()
               .replaceAll("\\s+", "-")
               .replaceAll("[^a-z0-9-]", "")
               .replaceAll("^-+|-+$", "");
}
```

- [ ] **Step 4: 테스트 통과 확인**

```bash
cd text-utils && mvn test
```
Expected: `BUILD SUCCESS`, 10 tests passed

- [ ] **Step 5: Commit**

```bash
git add src/
git commit -m "feat: implement slugify with TDD"
```

- [ ] **Step 6: PR 생성 및 merge**

```bash
git push -u origin feature/task-3-slugify
```

GitHub에서 PR 생성 → main으로 merge → 로컬 main 업데이트:

```bash
git checkout main && git pull
```

---

### Task 4: `countWords` 구현 (TDD)

**Files:**
- Modify: `src/test/java/TextUtilsTest.java`
- Modify: `src/main/java/TextUtils.java`

- [ ] **Step 0: feature 브랜치 생성**

```bash
git checkout -b feature/task-4-count-words
```

- [ ] **Step 1: 실패하는 테스트 작성**

`TextUtilsTest.java`에 추가:
```java
@Test
void countWords_basicSentence_returnsWordCount() {
    assertEquals(3, TextUtils.countWords("hello world foo"));
}

@Test
void countWords_multipleSpaces_countedCorrectly() {
    assertEquals(2, TextUtils.countWords("hello   world"));
}

@Test
void countWords_leadingTrailingSpaces_ignored() {
    assertEquals(2, TextUtils.countWords("  hello world  "));
}

@Test
void countWords_emptyOrNull_returnsZero() {
    assertEquals(0, TextUtils.countWords(""));
    assertEquals(0, TextUtils.countWords(null));
}

@Test
void countWords_singleWord_returnsOne() {
    assertEquals(1, TextUtils.countWords("hello"));
}
```

- [ ] **Step 2: 테스트 실행해서 실패 확인**

```bash
cd text-utils && mvn test
```
Expected: FAIL — `cannot find symbol: method countWords`

- [ ] **Step 3: 최소 구현 작성**

`TextUtils.java`에 추가:
```java
public static int countWords(String text) {
    if (text == null || text.isBlank()) return 0;
    return text.trim().split("\\s+").length;
}
```

- [ ] **Step 4: 테스트 통과 확인**

```bash
cd text-utils && mvn test
```
Expected: `BUILD SUCCESS`, 15 tests passed

- [ ] **Step 5: Commit**

```bash
git add src/
git commit -m "feat: implement countWords with TDD"
```

- [ ] **Step 6: PR 생성 및 merge**

```bash
git push -u origin feature/task-4-count-words
```

GitHub에서 PR 생성 → main으로 merge → 로컬 main 업데이트:

```bash
git checkout main && git pull
```

---

### Task 5: 코드 리뷰

- [ ] **Step 1: `/superpowers:requesting-code-review` 스킬 호출**

구현 완료 후 code-review 스킬을 호출하여 `TextUtils.java`와 `TextUtilsTest.java`를 검토한다.

- [ ] **Step 2: 리뷰 피드백 반영 (있을 경우)**

- [ ] **Step 3: 최종 Commit**

```bash
git add text-utils/src/
git commit -m "refactor: apply code review feedback"
```
