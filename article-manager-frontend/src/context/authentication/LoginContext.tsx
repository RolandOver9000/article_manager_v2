import { createContext, PropsWithChildren, useState } from "react";
import Axios from "axios";

type PropsType = {
    children: PropsWithChildren<{}>
}

export type LoginDataType = {
    email: string;
    password: string;
}

export interface LoginContextType {
    tryLogin: (inputs: LoginDataType) => void;
    loginError: String;
    clearLoginError: () => void;
    checkIfLoggedIn: () => void;
    isLoggedIn: boolean;
    showLoginModal: boolean;
    setShowLoginModal: React.Dispatch<React.SetStateAction<boolean>>;
}

export const LoginContext = createContext<LoginContextType>({} as LoginContextType);

export const LoginProvider = (props: PropsType) => {

    const[loginError, setLoginError] = useState<String>("");
    const[showLoginModal, setShowLoginModal] = useState<boolean>(false);
    const[isLoggedIn, setIsLoggedIn] = useState<boolean>(false);


    const checkIfLoggedIn = () => {
      Axios.get(
        process.env.REACT_APP_API_BACKEND_URL + "/auth/is-logged-in", {
          headers: {
            "Content-Type": "application/json",
          },
        withCredentials: true
      }).then((resp) => {
        setIsLoggedIn(resp.data);
      })
        .catch((error) => {
          console.log(error);
          setIsLoggedIn(false);
        });
    };

    const clearLoginError = () => {
      setLoginError("");
    }

    const tryLogin = (inputs: LoginDataType) => {
      clearLoginError();
      login(inputs);
    }

    const login = (inputs: LoginDataType) => {
        Axios.post(process.env.REACT_APP_API_BACKEND_URL + "/auth/login", inputs, {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true
        }).then((_resp) => {
          window.location.reload();
        }).catch(function (error) {
          setLoginError(error.response.data.message);
        });
      };

    return (
        <LoginContext.Provider
          value={{
            tryLogin,
            loginError,
            clearLoginError,
            checkIfLoggedIn,
            isLoggedIn,
            showLoginModal,
            setShowLoginModal}}>
          {props.children}
        </LoginContext.Provider>
      );
}