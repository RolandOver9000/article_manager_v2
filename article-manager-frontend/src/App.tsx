import 'antd/dist/antd.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route } from "react-router-dom";
import Authentication from './component/authentication/Authentication';
import Articles from './component/article/Articles';
import Home from './component/home/Home';
import UserProfile from './component/user_profile/UserProfile';
import { ArticleProvider } from './context/article/ArticleContext';
import { LoginProvider } from './context/authentication/LoginContext';
import PrivateRoute from './component/util/PrivateRoute';
import { UserProvider } from './context/user/UserContext';
import UserManager from './component/user_manager/UserManager';
import "./styles/main.css";
import { LogoutProvider } from './context/authentication/LogoutContext';

function App() {
  return (
    <Router>
      <LoginProvider>
        <LogoutProvider>
          <div className="App">

              <PrivateRoute exact path={"/"} component={Home} />

              <UserProvider>
                <PrivateRoute exact path={"/profile"} component={UserProfile} />
                <PrivateRoute exact path={"/user-manager"} component={UserManager} />
              </UserProvider>

              <Route exact path={"/authentication"} component={Authentication} />

              <ArticleProvider>
                  <PrivateRoute exact path={"/articles"} component={Articles}/>
              </ArticleProvider>

          </div>
        </LogoutProvider>
      </LoginProvider>
    </Router>
  );
}

export default App;
