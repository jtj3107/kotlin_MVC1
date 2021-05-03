class MemberRepository {
    var lastId = 0
    val members = mutableListOf<Member>()
    fun addMember(loginId: String, loginPw: String, name: String, nickName: String, cellPhoneNo: String, email: String): Int {
        val id = ++lastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()

        members.add(Member(id, regDate, updateDate, loginId, loginPw, name, nickName, cellPhoneNo, email))

        return id
    }

    fun isLoginIdAbleMember(loginId: String): Member? {
        for(member in members){
            if(member.loginId == loginId){
                return member
            }
        }
        return null
    }

    fun makeTestMember() {
        for(i in 1..9){
            addMember("user${i}", "user${i}", "user${i}", "user${i}", "user${i}", "user${i}")
        }
    }

    fun getMemberById(memberId: Int): Member? {
        for(member in members){
            if(member.id == memberId){
                return member
            }
        }
        return null
    }
}