package com.placodes.auth


//
//
//fun AuthenticationConfig.session() = this.session<UserModel>("session-auth") {
//    validate {session->
//        if(session.email.isNotEmpty() && session.password?.isNotEmpty() == true){
//            val user = UserModel(
//                id= session.id,
//                name= session.email,
//                email = session.email,
//                logged = true
//            )
//            user
//        }else null
//    }
//    challenge {
//        call.respondRedirect("/login")
//    }
//}
//
//
//fun AuthenticationConfig.tokenSession() = this.session<Token>("token-auth"){
//    validate {it ->
//        if(it.token.isNotEmpty()) it
//        else null
//    }
//}