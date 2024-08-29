package es.luisev.tareas;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.ui.VisorForm;
import es.luisev.tareas.utils.AppGlobalSingleton;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.ConfigurationSingleton;
import es.luisev.tareas.utils.Constantes;
import java.awt.EventQueue;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(TareasApplication.class);
        builder.headless(false);
        builder.run(args);
    }

    //  private static ConfigurableApplicationContext applicationContext;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        setupSpringContext();
        setupUIEnvironment();
        displayApp();
    }

    /**
     * Inicializa el contexto de Spring y lo pone a disposición del aplicativo
     * visual en el almacenamiento global.
     */
    private void setupSpringContext() throws TareasApplicationException {
        AppGlobalSingleton appGlobalSingleton = AppGlobalSingleton.getInstance();
        appGlobalSingleton.setProperty(Constantes.SPRING_CONTEXT, applicationContext);
        ConfigurationSingleton config;
        config = ConfigurationSingleton.getInstance();
        String codUsuario = config.getValue("default_user");
        appGlobalSingleton.setProperty(Constantes.DEFAULT_USER, AppHelper.getUsuarioService().findByCodigo(codUsuario));
    }

    /**
     *
     */
    private void setupUIEnvironment() {
        // Enter para los botones que tengan el foco
        UIManager.put("Button.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{"ENTER", "pressed", "released ENTER", "released"}));
    }

    /**
     *
     */
    private void displayApp() {
        EventQueue.invokeLater(() -> {
            VisorForm framePrincipal = new VisorForm();
            framePrincipal.setVisible(Boolean.TRUE);
        });
    }
}
