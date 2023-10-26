package negocio;

/**
 *
 * @author Cano
 */
public class Catastro {
    
    // Atributos
    VectorNBits v1; // solo para UV,MZ, lote, sup,
    VectorNBits v2; // solo para luz, agua , alc....etc.
    String nombre[];
    int cant;

    // Constructor
    public Catastro(int cant) {
        this.v1 = new VectorNBits(cant, 28);
        this.v2 = new VectorNBits(cant, 6);
        this.nombre = new String[cant +1];
        this.cant = 0;
    }
    
    public void insertar(String nombre, int uv, int mz, int lote, int sup,
                        int luz, int agua, int pav, int alc, int tel, int gas){
        
        cant++;
        int mask = uv;
        //System.out.println("mask= " + Integer.toBinaryString(mask));
        mask = mask << 6;
       // System.out.println("mask= " + Integer.toBinaryString(mask));
        mask = mask | mz;
        //System.out.println("mz=   " + Integer.toBinaryString(mask));
        mask = mask << 6;
        //System.out.println("mz=   " + Integer.toBinaryString(mask));
        mask = mask | lote;
        //System.out.println("lote= " + Integer.toBinaryString(mask));
        int sup2 = sup - 150;
        mask = mask << 9;
        //System.out.println("lote= " + Integer.toBinaryString(mask));
        mask = mask | sup2;
        //System.out.println("lote= " + Integer.toBinaryString(mask));
        int mask1 = luz;
        //System.out.println("mask1= " + Integer.toBinaryString(mask1));
        mask1 = mask1 << 1;
        mask1 = mask1 | agua;
        mask1 = mask1 << 1;
        mask1 = mask1 | pav;
        mask1 = mask1 << 1;
        mask1 = mask1 | alc;
        mask1 = mask1 << 1;
        mask1 = mask1 | tel;
        mask1 = mask1 << 1;
        mask1 = mask1 | gas;
        
        v1.insertar(mask, cant);
        v2.insertar(mask1, cant);
        this.nombre[cant -1] = nombre;
    }
    
    public int getCant(){
        return cant;
    }

    public String getNombre(int pos){
        return nombre[pos -1];
    }
    
    public int getUv(int pos){
        int dato = v1.sacar(pos);
        int mask = (int) (Math.pow(2, 7)-1);
        dato = dato >>> 21;
        dato = dato & mask;
        return dato;
    }
    
    public int getMz(int pos){
        int dato = v1.sacar(pos);
        int mask = (int) (Math.pow(2, 6)-1);
        dato = dato >>> 15;
        dato = dato & mask;
        return dato;
    }
    
    public int getLote(int pos){
        int dato = v1.sacar(pos);
        int mask = (int) (Math.pow(2, 6)-1);
        dato = dato >>> 9;
        dato = dato & mask;
        return dato;
    }
    
    public int getSup(int pos){
        int dato = v1.sacar(pos);
        int mask = (int) (Math.pow(2, 9)-1);
        //dato = dato >>> 0;
        dato = dato & mask;
        return dato + 150;
    }
    
   // ***********SERVICIOS BASICOS**************
    public String getLuz(int pos){
        int dato = v2.sacar(pos);
        int mask = (int) (Math.pow(2, 1)-1);
        dato = dato >>> 5;
        dato = dato & mask;
        if (dato == 1) {
            return "Si";
        } else {
            return "No";
        }
    }
    public String getAgua(int pos){
        int dato = v2.sacar(pos);
        int mask = (int) (Math.pow(2, 1)-1);
        dato = dato >>> 4;
        dato = dato & mask;
        if (dato == 1) {
            return "Si";
        } else {
            return "No";
        }
    }
    public String getPav(int pos){
        int dato = v2.sacar(pos);
        int mask = (int) (Math.pow(2, 1)-1);
        dato = dato >>> 3;
        dato = dato & mask;
        if (dato == 1) {
            return "Si";
        } else {
            return "No";
        }
    }
    public String getAlc(int pos){
        int dato = v2.sacar(pos);
        int mask = (int) (Math.pow(2, 1)-1);
        dato = dato >>> 2;
        dato = dato & mask;
        if (dato == 1) {
            return "Si";
        } else {
            return "No";
        }
    }
    public String getTel(int pos){
        int dato = v2.sacar(pos);
        int mask = (int) (Math.pow(2, 1)-1);
        dato = dato >>> 1;
        dato = dato & mask;
        if (dato == 1) {
            return "Si";
        } else {
            return "No";
        }
    }
    public String getGas(int pos){
        int dato = v2.sacar(pos);
        int mask = (int) (Math.pow(2, 1)-1);
        //dato = dato >>> 5;
        dato = dato & mask;
        if (dato == 1) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String mostrar(int pos){
        String S = "Catastro:\n";
        S = S + "Nro: " + pos +
                "\nNombre: " + getNombre(pos)+
                "\nUV: " + getUv(pos)+
                "\nMZ: " + getMz(pos)+
                "\nLote: " + getLote(pos)+
                "\nSuperficie: " + getSup(pos)+
                "\nLuz: " + getLuz(pos)+
                "\nAgua: " + getAgua(pos)+
                "\nPavimento: " + getPav(pos)+
                "\nAlcantarillado: " + getAlc(pos)+
                "\nTelefono: " + getTel(pos)+
                "\nGas: " + getGas(pos);
        return S;
    }

    public static void main(String[] args) {
        
        Catastro A = new Catastro(10);
        
        A.insertar("Juan", 1, 40, 25, 150, 1, 1, 
                    0, 1, 0, 1);
        System.out.println(A.mostrar(1));
    }



}
