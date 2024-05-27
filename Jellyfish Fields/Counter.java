import greenfoot.*;  // Importa a biblioteca Greenfoot

public class Counter extends Actor {
    private int value = 0;  // Valor do contador
    private String text;  // Texto exibido
    int fontSize = 32;  // Tamanho da fonte
    Color cor;
    // Construtor
    public Counter(String text, Color cor) {
        this.text = text;
        this.cor = cor;
        updateImage();
    }

    // Atualiza a imagem exibida com o texto
    private void updateImage() {
        GreenfootImage image = new GreenfootImage(text + value, fontSize, cor, null);
        setImage(image);
    }

    // Incrementa o valor do contador
    public void aumentar(int valor) {
        value += valor;
        updateImage();
    }

    // Decrementa o valor do contador
    public void decrement() {
        value--;
        updateImage();
    }

    // Define um valor específico para o contador
    public void setValue(int newValue) {
        value = newValue;
        updateImage();
    }

    // Retorna o valor atual do contador
    public int getValue() {
        return value;
    }
    
    void setFontSize(int tamanho) {
        fontSize = tamanho;
        updateImage();
    }
}