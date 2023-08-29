import { AuthContext } from "../../context/AuthContext.js"
import { useContext } from "react";



function PersonalAccount(){
    const  context  = useContext(AuthContext);
    return (
        <div>
            PersonalAccount {context.currentUser.username}
        </div>
    )
}

export { PersonalAccount }