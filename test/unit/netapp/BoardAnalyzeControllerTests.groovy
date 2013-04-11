package netapp



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(BoardAnalyzeController)
class BoardAnalyzeControllerTests {

    void testPatternMatch() {
       params.put(board, "0000110000000010010000011011000001001000000001100000000")
	   params.put(rows, 5)
	   params.put(column, 11)
	   params.put(pattern, "011010010110")
	   params.put(patternRows, 3) 
	   params.put(patternColumns, 4)
	   
	   BoardAnalyzeController.CountPattern()
    }
}
