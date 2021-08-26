import {useContext} from "react";
import Registration from "./Registration";
import Login from "./Login";
import { RegistrationProvider } from "../../context/authentication/RegistrationContext";
import { LoginContext } from "../../context/authentication/LoginContext";
import { Redirect } from "react-router";

export default function Authentication() {
    const {isLoggedIn} = useContext(LoginContext);

    if(isLoggedIn()) {
        return(
        <Redirect to="/" />);
    }
    return(
        <div className="authentication">
            <div className="authentication-menu">
                <RegistrationProvider>
                    <Registration />
                </RegistrationProvider>
                <Login/>
            </div>
        </div>
    );
}
