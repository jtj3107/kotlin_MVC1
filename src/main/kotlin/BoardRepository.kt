class BoardRepository {
    var lastId = 0
    val boards = mutableListOf<Board>()
    fun getBoardByName(name: String): Board? {
        for(board in boards){
            if(board.name == name){
                return board
            }
        }
        return null
    }

    fun getBoardByCode(code: String): Board? {
        for(board in boards){
            if(board.code == code){
                return board
            }
        }
        return null
    }

    fun makeBoard(name: String, code: String): Int {
        val id = ++lastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()

        boards.add(Board(id, regDate, updateDate, name, code))

        return id
    }

    fun makeTestBoard() {
        makeBoard("공지","notice")
        makeBoard("자유","free")
    }

    @JvmName("getBoards1")
    fun getBoards(): List<Board> {
        return boards
    }

    fun getBoardById(id: Int): Board? {
        for(board in boards){
            if(board.id == id){
                return board
            }
        }
        return null
    }

    fun boardRemove(boardToDelete: Board) {
        boards.remove(boardToDelete)
    }

    fun boardUpdate(boardToModify: Board, name: String, code: String) {
        boardToModify.name = name
        boardToModify.code = code
        boardToModify.updateDate = Util.getNowDateStr()
    }
}