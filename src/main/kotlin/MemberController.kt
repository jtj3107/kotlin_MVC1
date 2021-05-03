class MemberController {
    fun join(rq: Rq) {
        println("회원가입을 시작합니다.")
        print("사용하실 아이디 : ")
        val loginId = readLineTrim()

        val isLoginIdAbleMember = memberRepository.isLoginIdAbleMember(loginId)
        if(isLoginIdAbleMember != null){
            println("`${loginId}`(은)는 이미 사용중인 아이디입니다.")
            return
        }
        print("사용하실 비밀번호 : ")
        val loginPw = readLineTrim()
        print("이름 : ")
        val name = readLineTrim()
        print("닉네임 : ")
        val nickName = readLineTrim()
        print("휴대전화번호 : ")
        val cellPhoneNo = readLineTrim()
        print("이메일 : ")
        val email = readLineTrim()

        val id = memberRepository.addMember(loginId, loginPw, name, nickName, cellPhoneNo, email)
        println("${id}번 회원으로 등록 되었습니다.")
    }

    fun login(rq: Rq) {
        if(loginMember != null){
            println("이미 로그인 하셨습니다")
            return
        }
        print("아이디를 입력해주세요 : ")
        val loginId = readLineTrim()
        val member = memberRepository.isLoginIdAbleMember(loginId)
        if(member == null){
            println("`${loginId}`(은)는 존재하지 않는 아이디 입니다.")
            return
        }
        print("비밀번호를 입력해주세요 : ")
        val loginPw = readLineTrim()
        if(member.loginPw != loginPw){
            println("비밀번호가 틀렸습니다.")
            return
        }
        println("${member.nickName}님 환영합니다.")
        loginMember = member
    }

    fun logout(rq: Rq) {
        println("로그아웃 됩니다.")
        loginMember = null
    }
}