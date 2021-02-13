![Gradle Build](https://github.com/arhohuttunen/awstestkit/workflows/Gradle%20Build/badge.svg)
[![codecov](https://codecov.io/gh/arhohuttunen/awstestkit/branch/main/graph/badge.svg)](https://codecov.io/gh/arhohuttunen/awstestkit)
[![codefactor](https://www.codefactor.io/repository/github/arhohuttunen/awstestkit/badge/main)](https://www.codefactor.io/repository/github/arhohuttunen/awstestkit/overview/main)

# AWS TestKit

Programmer-friendly testing library for working with AWS SDK.

An example of ease-of-use with LocalStack and Testcontainers:

```java
@LocalStackTest
@SnsTest
class LocalStackExtensionTest {
    @Test
    void localStackIsRunning(@AwsClient SnsClient client) {
        assertIterableEquals(client.listTopics().topics(), emptyList());
    }
}
```

## Documentation

[User Guide]

[User Guide]: https://arhohuttunen.github.io/awstestkit/docs/snapshot
