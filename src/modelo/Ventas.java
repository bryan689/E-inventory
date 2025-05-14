package modelo;

import java.util.*;

public class Ventas {
    private List<String> historial;

    public Ventas() {
        historial = new ArrayList<>();
    }

    public List<String> getHistorial() {
        return historial;
    }

    public void setHistorial(List<String> historial) {
        this.historial = historial;
    }
    
}

