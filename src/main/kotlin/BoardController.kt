class BoardController {
    fun make(rq: Rq) {
        print("게시판 이름 : ")
        val name = readLineTrim()

        val boardName = boardRepository.getBoardByName(name)
        if(boardName != null){
            println("`${name}`(은)는 이미 사용중이 이름 입니다.")
            return
        }
        print("게시판 코드 : ")
        val code = readLineTrim()
        val boardCode = boardRepository.getBoardByCode(code)
        if(boardCode != null){
            println("`${code}`(은)는 이미 사용중이 코드 입니다.")
            return
        }
        val id = boardRepository.makeBoard(name, code)

        println("${id}번 게시판이 생성 되었습니다.")
    }

    fun list(rq: Rq) {
        val boards = boardRepository.getBoards()

        for(board in boards){
            println("번호 : ${board.id} / 이름 : ${board.name} / 코드 : ${board.code} / 등록날짜 : ${board.regDate}")
        }
    }

    fun delete(rq: Rq) {
        val id = rq.getIntParam("id", 0)

        if(id <= 0){
            println("id를 입력해주세요 ")
            return
        }
        val boardToDelete = boardRepository.getBoardById(id)
        if(boardToDelete == null){
            println("${id}번 게시판은 존재하지 않습니다.")
            return
        }
        boardRepository.boardRemove(boardToDelete)

        println("${id}번 게시판이 삭제 되었습니다.")
    }

    fun modify(rq: Rq) {
        val id = rq.getIntParam("id", 0)

        if(id <= 0){
            println("id를 입력해주세요 ")
            return
        }
        val boardToModify = boardRepository.getBoardById(id)
        if(boardToModify == null){
            println("${id}번 게시판은 존재하지 않습니다.")
            return
        }
        print("새 게시판 이름 : ")
        val name = readLineTrim()
        val boardName = boardRepository.getBoardByName(name)
        if(boardName != null){
            println("`${name}`(은)는 이미 사용중이 이름 입니다.")
            return
        }

        print("새 게시판 코드 : ")
        val code = readLineTrim()
        val boardCode = boardRepository.getBoardByCode(code)
        if(boardCode != null){
            println("`${code}`(은)는 이미 사용중이 코드 입니다.")
            return
        }
        boardRepository.boardUpdate(boardToModify, name, code)
        println("${id}번 게시판이 수정 되었습니다.")

    }
}