import { createContext, PropsWithChildren } from "react";

type PropsType = {
    children: PropsWithChildren<{}>
}


export interface LogoutContextType {
    logout: () => void;
}

export const LogoutContext = createContext<LogoutContextType>({} as LogoutContextType);

export const LogoutProvider = (props: PropsType) => {

    const logout = () => {
        //need an axios call for logout
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
