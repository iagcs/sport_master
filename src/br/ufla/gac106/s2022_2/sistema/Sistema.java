package br.ufla.gac106.s2022_2.sistema;

import br.ufla.gac106.s2022_2.sistema.views.menus.MenuPrincipal;

import java.util.Scanner;
class Sistema {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        MenuPrincipal menu = new MenuPrincipal();

        menu.menu();
    }
}