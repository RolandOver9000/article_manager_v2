import { createContext, PropsWithChildren, useState } from "react";
import Axios from "axios";
import { useCookies } from 'react-cookie';

type PropsType = {
    children: PropsWithChildren<{}>
}

export type LoginDataType = {
    email: string;
    password: string;
}

export interface LoginContextType {
    tryLogin: (inputs: LoginDataType) => void;
    loginErrors: Object;
    clearLoginErrors: () => void;
    isLoggedIn: () => boolean;
    showLoginModal: boolean;
    setShowLoginModal: React.Dispatch<React.SetStateAction<boolean>>;
}

export const LoginContext = createContext<LoginContextType>({} as LoginContextType);

export const LoginProvider = (props: PropsType) => {

    const[loginErrors, setLoginErrors] = useState<Object>({});
    const[cookies, setCookie] = useCookies([]);
    const[showLoginModal, setShowLoginModal] = useState<boolean>(false);


    const isLoggedIn = () => {
      if(cookies.token !== undefined) {
        return true;
      }
      return false;
    }

    const clearLoginErrors = () => {
      setLoginErrors({});
    }

    const tryLogin = (inputs: LoginDataType) => {
      clearLoginErrors();
      login(inputs);
    }

    const login = (inputs: LoginDataType) => {
        Axios.post(process.env.REACT_APP_API_BACKEND_URL + "/api/login", inputs, {
          headers: {
            "Content-Type": "application/json",
          },
        }).then((resp) => {
          setCookie('token', resp.data.user.token, { path: '/' , sameSite: "strict"});
        }).then(() => {
          window.location.reload();
        }).catch(function (error) {
          setLoginErrors(error.response.data.errors);
        });
      };

    return (
        <LoginContext.Provider
          value={{
            tryLogin,
            loginErrors,
            clearLoginErrors,
            isLoggedIn,
            showLoginModal,
            setShowLoginModal}}>
          {props.children}
        </LoginContext.Provider>
      );
}
