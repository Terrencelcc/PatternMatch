package netapp
import grails.converters.*

class BoardAnalyzeController {

	def index() {
	}

	def CountPattern(){
		println "called"


		//CountPattern?board="0000110000000010010000011011000001001000000001100000000"&rows=5&columns=11&&pattern="011010010110"&patternRows=3&patternColumns=4

		char[] pattern = params.pattern.toCharArray()
		int patternRows = params.patternRows.toLong()
		int patternColumns = params.patternColumns.toLong()

		char[] board = params.board.toCharArray()
		int rows = params.rows.toLong()
		int columns = params.columns.toLong()

		int length=rows+columns
		char[][] bitBoard
		bitBoard=new char[length][length]
		char[][] bitPattern
		bitPattern=new char[length][length]


		//		for(int i=rows ; i>0 ; --i) {
		//			for(int j=columns ; j>0 ; --j) {
		//				if(board[rows*(i-1)+j-1].toString()=='1'){
		//					bitBoard[i-1][j-1]='1'
		//				}
		//				else {
		//					bitBoard[i-1][j-1]='0'
		//				}
		//			}
		//		}

		for(int i=0 ; i<rows ; ++i) {
			for(int j=0 ; j<columns ; ++j) {
				if(board[columns*i+j].toString()=='1'){
					bitBoard[i][j]='1'
				}
				else {
					bitBoard[i][j]='0'
				}
			}
		}

		for(int i=0 ; i<patternRows; ++i){
			for(int j=0 ; j<patternColumns; ++j){
				if(pattern[patternColumns*i+j].toString()=='1'){
					bitPattern[i][j]='1'
				}
				else {
					bitPattern[i][j]='0'
				}
			}
		}


		//		long flag = 0x1
		//		for(long i=rows ; i>0 ; --i) {
		//			for(long j=columns ; j>0 ; --j) {
		//				bitBoard[i][j]=board& flag
		//				flag << 1
		//			}
		//		}


		//		int flag2 = 0x1
		//		for(int i=patternRows ; i>0; --i){
		//			for(int j=patternColumns ; j>0; --j){
		//				bitPattern[i][j]=pattern& flag2
		//				flag << 1
		//			}
		//		}

		int numOfPattern
		boolean match=true
		for(int i=0; i<rows; ++i) {
			for(int j=0; j<columns; ++j) {
				int tempI=i
				//int tempJ=j
				for(int k=0; k<patternRows; ) {
					int tempJ=j
					for(int s=0; s<patternColumns; ) {
						if(bitPattern[k][s]!= bitBoard[tempI][tempJ]) {
							match=false
						}
						if(match==false) {
							break
						}
						++s; ++tempJ;
					}
					if(match==false) {
						break
					}
					++k; ++tempI;
				}
				if(match==true){
					++numOfPattern
				}
				match=true
			}
		}
		println numOfPattern
		
		render(contentType: "text/xml"){
			result{
				NumberOfPattern(numOfPattern)
			}
		}
	}
}
