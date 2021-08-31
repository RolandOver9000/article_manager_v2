import { useContext, useEffect } from "react";
import { Redirect, Route } from "react-router-dom";
import { LoginContext } from "../../context/authentication/LoginContext";

interface PropsType {
  exact?: boolean;
  path: string;
  component: React.ComponentType<any>;
}

export default function PrivateRoute({ component: Component, ...rest }: PropsType) {
  const { checkIfLoggedIn, isLoggedIn } = useContext(LoginContext);

  useEffect(() => {
    checkIfLoggedIn()
  }, [isLoggedIn])

  return (
    <Route {...rest} render={props => (
      isLoggedIn ?
          <Component {...props} />
      : <Redirect to="/authentication" />
  )} />
  );
}