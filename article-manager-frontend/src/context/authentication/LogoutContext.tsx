import { createContext, PropsWithChildren } from "react";
import { useCookies } from 'react-cookie';

type PropsType = {
    children: PropsWithChildren<{}>
}


export interface LogoutContextType {
    logout: () => void;
}

export const LogoutContext = createContext<LogoutContextType>({} as LogoutContextType);

export const LogoutProvider = (props: PropsType) => {
    const[_cookies, _setCookie, removeCookie] = useCookies([]);

    const logout = () => {
        removeCookie("token");
        window.location.reload();
    }

    return(
        <LogoutContext.Provider
        value={{
            logout
        }}>
            {props.children}
        </LogoutContext.Provider>
    )
}
