val articleRepository = ArticleRepository()
val memberRepository = MemberRepository()
val boardRepository = BoardRepository()
var loginMember : Member? = null
fun main(){
    println("== 게시판 프로그램 시작 ==")
    val systemController = SystemController()
    val articleController = ArticleController()
    val memberController = MemberController()
    val boardController = BoardController()
    boardRepository.makeTestBoard()
    memberRepository.makeTestMember()
    articleRepository.makeTestArticle()

    while(true){
        val prompt = if(loginMember == null){
            print("명령어 : ")
        }else {
            print("${loginMember!!.nickName}님 : ")
        }
        val command = readLineTrim()

        val rq = Rq(command)

        when(rq.actionPath){
            "/system/exit" -> {
                systemController.exit(rq)
                break
            }
            "/article/write" -> {
                articleController.write(rq)
            }
            "/article/list" -> {
                articleController.list(rq)
            }
            "/article/delete" ->{
                articleController.delete(rq)
            }
            "/article/modify" -> {
                articleController.modify(rq)
            }
            "/article/detail" -> {
                articleController.detail(rq)
            }
            "/member/join" ->{
                memberController.join(rq)
            }
            "/member/login" -> {
                memberController.login(rq)
            }
            "/member/logout" -> {
                memberController.logout(rq)
            }
            "/board/add" -> {
                boardController.make(rq)
            }
            "/board/list" -> {
                boardController.list(rq)
            }
            "/board/delete" ->{
                boardController.delete(rq)
            }
            "/board/modify" -> {
                boardController.modify(rq)
            }
        }
    }
    println("== 게시판 프로그램 끝 ==")
}
fun readLineTrim() = readLine()!!.trim()