package mx.uam.azc.dnasystem.sunu.data;

/**
*************************************************
* DNA System                                    *
* Por imposible que parezca ¡Tiene Solución!    *
*                                               *
* José Enrique García Ramírez        2163033941 *
* Tania Guadalupe Zárate Chávez      2173075371 *
* Christopher Yael Meneses Martínez  2152001568 *
* Hurtado Avilés Gabriel             2172000781 *
*                                               *
* Taller de desarrollo de aplicaciónes web      *
* Hugo Pablo Leyva                              *
* 13/Agosto/2021                                *
*************************************************
*/

import java.io.Serializable;

/**
 * @author vekz
 * DNA System
 *
 */
public class ProductoDTO implements Serializable
{
  private String _id;
  private String _nombre;
  private int _precio;
  private int _cantidad;
  private String _descripcion;
  private int _modelo;
  
  /**
   * @return the id
   */
  public String getId()
  {
    return _id;
  }
  /**
   * @param id the id to set
   */
  public void setId( String id )
  {
    _id = id;
  }
  /**
   * @return the nombre
   */
  public String getNombre()
  {
    return _nombre;
  }
  /**
   * @param nombre the nombre to set
   */
  public void setNombre( String nombre )
  {
    _nombre = nombre;
  }
  /**
   * @return the precio
   */
  public int getPrecio()
  {
    return _precio;
  }
  /**
   * @param precio the precio to set
   */
  public void setPrecio( int precio )
  {
    _precio = precio;
  }
  /**
   * @return the cantidad
   */
  public int getCantidad()
  {
    return _cantidad;
  }
  /**
   * @param cantidad the cantidad to set
   */
  public void setCantidad( int cantidad )
  {
    _cantidad = cantidad;
  }
  /**
   * @return the descripcion
   */
  public String getDescripcion()
  {
    return _descripcion;
  }
  /**
   * @param descripcion the descripcion to set
   */
  public void setDescripcion( String descripcion )
  {
    _descripcion = descripcion;
  }
  /**
   * @return the modelo
   */
  public int getModelo()
  {
    return _modelo;
  }
  /**
   * @param modelo the modelo to set
   */
  public void setModelo( int modelo )
  {
    _modelo = modelo;
  }
  
  
}
