package modulos;

public class IntervaloAntiguo {

	private double inferior;

	private double superior;

	public IntervaloAntiguo(double inferior, double superior) {
		assert inferior <= superior;
		this.inferior = inferior;
		this.superior = superior;
	}

	public IntervaloAntiguo(double superior) {
		this(0, superior);
	}

	public IntervaloAntiguo(IntervaloAntiguo intervalo) {
		this(intervalo.inferior, intervalo.superior);
	}

	public IntervaloAntiguo() {
		this(0, 0);
	}

	public IntervaloAntiguo clone() {
		return new IntervaloAntiguo(this);
	}

	public double longitud() {
		return superior - inferior;
	}

	public void desplazar(double desplazamiento) {
		superior += desplazamiento;
		inferior += desplazamiento;
	}

	public IntervaloAntiguo desplazado(double desplazamiento) {
		return new IntervaloAntiguo(inferior + desplazamiento, superior + desplazamiento);
	}

	public boolean incluye(double valor) {
		return inferior <= valor && valor <= superior;
	}

	public boolean incluye(IntervaloAntiguo intervalo) {
		assert intervalo != null;
		return this.incluye(intervalo.inferior) && this.incluye(intervalo.superior);
	}

	public boolean equals(IntervaloAntiguo intervalo) {
		assert intervalo != null;
		return this.superior == intervalo.superior && this.inferior == intervalo.inferior;
	}

	public IntervaloAntiguo interseccion(IntervaloAntiguo intervalo) {
		assert this.intersecta(intervalo);
		if (this.incluye(intervalo)) {
			return intervalo.clone();
		} else if (intervalo.incluye(this)) {
			return this.clone();
		} else if (this.incluye(intervalo.inferior)) {
			return new IntervaloAntiguo(intervalo.inferior, superior);
		} else {
			return new IntervaloAntiguo(inferior, intervalo.superior);
		}
	}

	public boolean intersecta(IntervaloAntiguo intervalo) {
		return this.incluye(intervalo.inferior) || this.incluye(intervalo.superior) || intervalo.incluye(this);
	}

	public void oponer() {
		double var = superior;
		superior = -inferior;
		inferior = -var;
	}

	public void doblar() {
		double longitudInicial = this.longitud();
		superior += longitudInicial / 2;
		inferior -= longitudInicial / 2;
	}

	public void recoger() {
		GestorIO gestorIO = new GestorIO();
		gestorIO.out("inferior?");
		inferior = gestorIO.inDouble();
		gestorIO.out("superior?");
		superior = gestorIO.inDouble();
	}

	public void mostrar() {
	new GestorIO().out("[" + inferior + "," + superior + "]");
		System.out.println();
	}

//Mi primer metodo propio y muy orgulloso de ello;)
	public IntervaloAntiguo[] trocear(int trozos) {
		double largoTrozo = this.longitud() / trozos;
		IntervaloAntiguo[] intervalo = new IntervaloAntiguo[trozos];
		for (int i = 0; i <= trozos - 1; i++) {
			intervalo[i] = new IntervaloAntiguo(inferior + i * largoTrozo, inferior + largoTrozo * (i + 1));
			new GestorIO().out("El trozo numero: " + (i + 1));
			intervalo[i].mostrar();
		}
		return intervalo;
	}

	public static void main(String[] args) {
		IntervaloAntiguo intervalo1 = new IntervaloAntiguo(4, 16);
		intervalo1.mostrar();
		intervalo1.doblar();
		intervalo1.mostrar();
		intervalo1.trocear(6);

	}
}
//Los constructores no construyen nada!!!