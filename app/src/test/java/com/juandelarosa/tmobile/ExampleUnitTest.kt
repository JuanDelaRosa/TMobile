package com.juandelarosa.tmobile

import com.juandelarosa.data.mappers.TMobileDBMapper
import com.juandelarosa.data.mappers.TMobileMapper
import com.juandelarosa.domain.entities.CardsDB
import org.junit.Test
import java.io.BufferedReader

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testMapper() {
        val inp = this.javaClass.classLoader.getResourceAsStream("response.txt")
        val reader = BufferedReader(inp.reader())
        val content = StringBuilder()
        reader.use { reader ->
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        }
        val mapper = TMobileMapper()
        var response = mapper.testToFeeds(content.toString())
        val result = mapper.toFeeds(response)
        assert(result.count()>0)
    }
    @Test
    fun testDBMapper() {
        val inp = this.javaClass.classLoader.getResourceAsStream("response.txt")
        val reader = BufferedReader(inp.reader())
        val content = StringBuilder()
        reader.use { reader ->
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        }
        val mapper = TMobileMapper()
        var response = mapper.testToFeeds(content.toString())
        val result = mapper.toFeeds(response)
        val resultDB = TMobileDBMapper().toBackup(CardsDB(0,result))
        assert(resultDB.info!="")
    }
}