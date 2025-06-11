package ProcessEngine.GraphicCore;

import ProcessEngine.GraphicCore.SignWindow.SignWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class GraphicRun extends Application  {

    protected Stage stage;

    public static void main() {
        Application.launch(GraphicRun.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        SignWindow startSignWindow = new SignWindow(stage);
        // if (startSignWindow.getAuth()) {
            startSignWindow.getAuth();
        // }
    }

    protected void getCollectionData() {

    }

    public class AuthCheck {
        protected boolean authSuccess;
        protected String login;

        public AuthCheck(boolean authSuccess) {
            this.authSuccess = authSuccess;
        }

        protected void getLogin(String login) {
            this.login = login;
        }
    }

}
