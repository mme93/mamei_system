package mamei.de;


import mamei.de.mdv.MDV;

public class Main {

    public static void main(String[] args) {
        System.out.println("MDV System Runs");
        MDV mdv = MDV.builder().withGenerator().build();
        mdv.getLoadedSystemNames().forEach(x-> System.out.println(x));

    }
}