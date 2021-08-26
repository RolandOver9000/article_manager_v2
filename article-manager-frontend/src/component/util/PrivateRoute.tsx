import { useContext } from "react";
import { Redirect, Route } from "react-router-dom";
import { LoginContext } from "../../context/authentication/LoginContext";

interface PropsType {
  exact?: boolean;
  path: string;
  component: React.ComponentType<any>;
}

export default function PrivateRoute({ component: Component, ...rest }: PropsType) {
  const { isLoggedIn } = useContext(LoginContext);

  return (
    <Route {...rest} render={props => (
      isLoggedIn() ?
          <Component {...props} />
      : <Redirect to="/authentication" />
  )} />
  );
}
