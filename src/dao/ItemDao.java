/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Item;


/**
 *
 * @author aluno
 */
public interface ItemDao {
    
    public void inserir(Item item);
    public void alterar(Item item);
    public void excluir(Item item);
    public void buscar(int id);
    public ArrayList listar();
    
}
