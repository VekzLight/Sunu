/**
 * Creado: Sep 7, 2021 7:54:25 PM
 */
package mx.uam.azc.dnasystem.sunu.data;

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
}
