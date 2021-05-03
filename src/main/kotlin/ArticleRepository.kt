class ArticleRepository {
    var lastId = 0
    val articles = mutableListOf<Article>()
    fun addArticle(boardId: Int, memberId: Int, title: String, body: String): Int {
        val id = ++lastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()

        articles.add(Article(id, regDate, updateDate, title, body, memberId, boardId))

        return id
    }

    fun makeTestArticle() {
        for (i in 1..25) {
            addArticle(i % 2 + 1, i % 9 + 1, "제목${i}", "내용${i}")
        }
    }

    fun filteredArticles(boardCode: String, page: Int, searchKeyword: String, pageCount: Int): List<Article> {
        val filtered1Articles = getSearchKeywordFilteredArticles(articles,boardCode, searchKeyword)
        val filtered2Articles = getPageFilteredArticles(filtered1Articles, page, pageCount)

        return filtered2Articles
    }

    private fun getPageFilteredArticles(filtered1Articles: List<Article>, page: Int, pageCount: Int): List<Article> {
        val filteredArticles = mutableListOf<Article>()

        val fromIndex = (page - 1) * pageCount
        val startIndex = filtered1Articles.lastIndex - fromIndex
        var endIndex = startIndex - pageCount + 1
        if (endIndex < 0) {
            endIndex = 0
        }
        for (i in startIndex downTo endIndex) {
            filteredArticles.add(filtered1Articles[i])
        }
        return filteredArticles
    }

    private fun getSearchKeywordFilteredArticles(articles: List<Article>, boardCode: String, searchKeyword: String): List<Article> {
        if(boardCode.isEmpty() && searchKeyword.isEmpty()){
            return articles
        }
        val filteredArticles = mutableListOf<Article>()
        val boardId = if(boardCode.isEmpty()){
            0
        }else{
            boardRepository.getBoardByCode(boardCode)!!.id
        }

        for(article in articles){
            if(boardId != 0){
                if(article.boardId != boardId){
                    continue
                }
            }
            if(searchKeyword.isNotEmpty()){
                if(!article.title.contains(searchKeyword))
                    continue
            }
            filteredArticles.add(article)
        }
        return filteredArticles
    }

    fun articleById(id: Int): Article? {
        for (article in articles) {
            if (article.id == id) {
                return article
            }
        }
        return null
    }

    fun articleRemove(articleToDelete: Article) {
        articles.remove(articleToDelete)
    }

    fun articleUpdate(articleToModify: Article, title: String, body: String) {
        articleToModify.title = title
        articleToModify.body = body
        articleToModify.updateDate = Util.getNowDateStr()
    }
}