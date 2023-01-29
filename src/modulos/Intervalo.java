package modulos;

public class Intervalo {

	private double puntoMedio;

	private double longitud;

	private double getInferior() {
		return puntoMedio - longitud/2;
	}

	private void setInferior(double inferior) {
		longitud = this.getSuperior() - inferior;
		puntoMedio = inferior + longitud/2;
	}

	private double getSuperior() {
		return puntoMedio + longitud/2;
	}

	private void setSuperior(double superior) {
		longitud = superior - this.getInferior();
		puntoMedio = superior - longitud/2;	}

	public Intervalo(double inferior, double superior) {
		assert inferior <= superior;
		this.setInferior(inferior);
		this.setSuperior(superior);
	}

	public Intervalo(double superior) {
		this(0, superior);
	}

	public Intervalo(Intervalo intervalo) {
		this(intervalo.getInferior(), intervalo.getSuperior());
	}

	public Intervalo() {
		this(0, 0);
	}

	public Intervalo clone() {
		return new Intervalo(this);
	}

	public double longitud() {
		return longitud;
	}

	public void desplazar(double desplazamiento) {
		setSuperior(getSuperior() + desplazamiento);
		setInferior(getInferior() + desplazamiento);
	}

	public Intervalo desplazado(double desplazamiento) {
		return new Intervalo(getInferior() + desplazamiento, getSuperior() + desplazamiento);
	}

	public boolean incluye(double valor) {
		return getInferior() <= valor && valor <= getSuperior();
	}

	public boolean incluye(Intervalo intervalo) {
		assert intervalo != null;
		return this.incluye(intervalo.getInferior()) && this.incluye(intervalo.getSuperior());
	}

	public boolean equals(Intervalo intervalo) {
		assert intervalo != null;
		return this.getSuperior() == intervalo.getSuperior() && this.getInferior() == intervalo.getInferior();
	}

	public Intervalo interseccion(Intervalo intervalo) {
		assert this.intersecta(intervalo);
		if (this.incluye(intervalo)) {
			return intervalo.clone();
		} else if (intervalo.incluye(this)) {
			return this.clone();
		} else if (this.incluye(intervalo.getInferior())) {
			return new Intervalo(intervalo.getInferior(), getSuperior());
		} else {
			return new Intervalo(getInferior(), intervalo.getSuperior());
		}
	}

	public boolean intersecta(Intervalo intervalo) {
		return this.incluye(intervalo.getInferior()) || this.incluye(intervalo.getSuperior()) || intervalo.incluye(this);
	}

	public void oponer() {
		double var = getSuperior();
		setSuperior(-getInferior());
		setInferior(-var);
	}

	public void doblar() {
		double longitudInicial = this.longitud();
		setSuperior(getSuperior() + longitudInicial / 2);
		setInferior(getInferior() - longitudInicial / 2);
	}

	public void recoger() {
		GestorIO gestorIO = new GestorIO();
		gestorIO.out("inferior?");
		setInferior(gestorIO.inDouble());
		gestorIO.out("superior?");
		setSuperior(gestorIO.inDouble());
	}

	public void mostrar() {
	new GestorIO().out("[" + getInferior() + "," + getSuperior() + "]");
		System.out.println();
	}

//Mi primer metodo propio y muy orgulloso de ello;)
	public Intervalo[] trocear(int trozos) {
		double largoTrozo = this.longitud() / trozos;
		Intervalo[] intervalo = new Intervalo[trozos];
		for (int i = 0; i <= trozos - 1; i++) {
			intervalo[i] = new Intervalo(getInferior() + i * largoTrozo, getInferior() + largoTrozo * (i + 1));
			new GestorIO().out("El trozo numero: " + (i + 1));
			intervalo[i].mostrar();
		}
		return intervalo;
	}

	public static void main(String[] args) {
		Intervalo intervalo1 = new Intervalo(4, 16);
		intervalo1.mostrar();
		intervalo1.doblar();
		intervalo1.mostrar();
		intervalo1.trocear(6);

	}
}
//Los constructores no construyen nada!!!