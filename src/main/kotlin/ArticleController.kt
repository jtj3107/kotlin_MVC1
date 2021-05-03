class ArticleController {
    fun write(rq: Rq) {
        if(loginMember == null){
            println("로그인후 사용 가능합니다.")
            return
        }
        println("게시판 번호를 선택 해주세요.")
        val boards = boardRepository.getBoards()
        for(board in boards){
            println("번호 : ${board.id} / 이름 : ${board.name}")
        }
        val boardId = readLineTrim().toInt()
        val board = boardRepository.getBoardById(boardId)
        if(board!!.id == null){
            println("${boardId}번 게시판은 존재하지 않습니다.")
            return
        }
        print("제목 : ")
        val title = readLineTrim()
        print("내용 : ")
        val body = readLineTrim()

        val id = articleRepository.addArticle(board.id, loginMember!!.id, title, body)

        println("${id}번 게시물이 등록 되었습니다.")
    }

    fun list(rq: Rq) {
        val page = rq.getIntParam("page", 1)
        val searchKeyword = rq.getStringParam("searchKeyword", "")
        val boardCode = rq.getStringParam("boardCode", "")

        val filteredArticles = articleRepository.filteredArticles(boardCode, page, searchKeyword, 10)

        for(article in filteredArticles){
            val writes = memberRepository.getMemberById(article.memberId)!!
            val board = boardRepository.getBoardById(article.boardId)!!
            println("게시판 이름 : ${board.name} / 번호 : ${article.id} / 제목 : ${article.title} / 등록날짜 : ${article.regDate} / 작성자 : ${writes.nickName}")
        }
    }

    fun delete(rq: Rq) {
        if(loginMember == null){
            println("로그인후 사용 가능합니다.")
            return
        }
        val id = rq.getIntParam("id", 0)

        if(id <= 0){
            println("id를 입력해주세요")
            return
        }
        val articleToDelete = articleRepository.articleById(id)
        if(articleToDelete == null){
            println("${id}번 게시물은 존재 하지않습니다.")
            return
        }
        if(loginMember!!.id != articleToDelete.memberId){
            println("해당글 작성자만 삭제 가능합니다.")
            return
        }
        articleRepository.articleRemove(articleToDelete)

        println("${id}번 게시물이 삭제되었습니다.")
    }

    fun modify(rq: Rq) {
        if(loginMember == null){
            println("로그인후 사용 가능합니다.")
            return
        }
        val id = rq.getIntParam("id", 0)

        if(id <= 0){
            println("id를 입력해주세요")
            return
        }
        val articleToModify = articleRepository.articleById(id)
        if(articleToModify == null){
            println("${id}번 게시물은 존재 하지않습니다.")
            return
        }
        if(loginMember!!.id != articleToModify.memberId){
            println("해당글 작성자만 수정 가능합니다.")
            return
        }
        print("새 제목 : ")
        val title = readLineTrim()
        print("새 내용 : ")
        val body = readLineTrim()

        articleRepository.articleUpdate(articleToModify, title, body)
        println("${id}번 게시물이 수정 되었습니다.")
    }

    fun detail(rq: Rq) {
        val id = rq.getIntParam("id", 0)

        if(id <= 0){
            println("id를 입력해주세요")
            return
        }
        val articleToModify = articleRepository.articleById(id)
        if(articleToModify == null){
            println("${id}번 게시물은 존재 하지않습니다.")
            return
        }
        println("번호 : ${articleToModify.id}")
        println("제목 : ${articleToModify.title}")
        println("내용 : ${articleToModify.body}")
        println("등록날짜 : ${articleToModify.regDate}")
        println("갱신날짜 : ${articleToModify.updateDate}")
    }
}