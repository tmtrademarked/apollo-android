package com.apollographql.apollo.gradle.test

import com.apollographql.apollo.compiler.child
import com.apollographql.apollo.gradle.util.TestUtils
import com.apollographql.apollo.gradle.util.TestUtils.withSimpleProject
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class DownloadSchemaTests {
  val mockServer = MockWebServer()

  val apolloConfiguration = """
      apollo {
        service("mock") {
          schemaPath = "com/example/schema.json"
          introspection {
            endpointUrl = "${mockServer.url("/").url()}"
          }
        }
      }
    """.trimIndent()

  @Test
  fun `schema is downloaded correctly`() {

    withSimpleProject(apolloConfiguration = apolloConfiguration) { dir ->
      val content = "schema should be here"
      val mockResponse = MockResponse().setBody(content)
      mockServer.enqueue(mockResponse)

      TestUtils.executeTask("downloadMockApolloSchema", dir)

      assertEquals(content, dir.child("src", "main", "graphql", "com", "example", "schema.json").readText())
    }
  }

  @Test
  fun `download schema is never up-to-date`() {

    withSimpleProject(apolloConfiguration = apolloConfiguration) { dir ->
      val content = "schema should be here"
      val mockResponse = MockResponse().setBody(content)
      mockServer.enqueue(mockResponse)

      var result = TestUtils.executeTask("downloadMockApolloSchema", dir)
      assertEquals(TaskOutcome.SUCCESS, result.task(":downloadMockApolloSchema")?.outcome)

      mockServer.enqueue(mockResponse)

      // Since the task does not declare any output, it should never be up-to-date
      result = TestUtils.executeTask("downloadMockApolloSchema", dir)
      assertEquals(TaskOutcome.SUCCESS, result.task(":downloadMockApolloSchema")?.outcome)

      assertEquals(content, dir.child("src", "main", "graphql", "com", "example", "schema.json").readText())
    }
  }

  @Test
  fun `download schema is never cached`() {

    withSimpleProject(apolloConfiguration = apolloConfiguration) { dir ->
      val buildCacheDir = dir.child("buildCache")

      File(dir, "settings.gradle").appendText("""
        
        buildCache {
            local {
                directory '${buildCacheDir.absolutePath}'
            }
        }
      """.trimIndent())

      val schemaFile = dir.child("src", "main", "graphql", "com", "example", "schema.json")

      val content1 = "schema1"
      mockServer.enqueue(MockResponse().setBody(content1))

      TestUtils.executeTask("downloadMockApolloSchema", dir, "--build-cache")
      assertEquals(content1, schemaFile.readText())

      val content2 = "schema2"
      mockServer.enqueue(MockResponse().setBody(content2))

      TestUtils.executeTask("downloadMockApolloSchema", dir, "--build-cache")
      assertEquals(content2, schemaFile.readText())
    }
  }
}