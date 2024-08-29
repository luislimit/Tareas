package es.luisev.tareas.ui.table.cabecera;

import es.luisev.tareas.utils.UIHelper;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LVARONA
 *
 */
@Slf4j
public abstract class Cabecera {
    @Getter
    protected List<String> columnIdentifiers;

    @Getter
    protected List<Class<?>> columnClasses;

    @Getter
    protected List<Integer> columnSizes;

    /**
     *
     */
    public Cabecera() {
        super();

        initialize();
    }

    /**
     *
     */
    private void initialize() {
        columnIdentifiers = new ArrayList<>();
        columnClasses = new ArrayList<>();
        columnSizes = new ArrayList<>();

        setupCabecera();
    }
    
    public void addColumn(String clave){
        columnIdentifiers.add(UIHelper.getLiteral(clave));
    }

    /**
     *
     */
    public abstract void setupCabecera();

    /**
     * @param index
     * @return
     */
    public String getIdentifierAt(Integer index) {
        return columnIdentifiers.get(index);
    }

    /**
     * @param index
     * @return
     */
    public Class<?> getClassAt(Integer index) {
        return columnClasses.get(index);
    }

    /**
     * @param index
     * @return
     */
    public Integer getSizeColumn(Integer index) {
        return columnSizes.get(index);
    }
}
