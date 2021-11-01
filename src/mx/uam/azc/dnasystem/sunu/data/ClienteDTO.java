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
public class ClienteDTO implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer _id;
  private String _nombre;
  private String _materno;
  private String _paterno;
  private Integer edad;
  private String _sexo;
  private UsuarioDTO _usuario;
  /**
   * @return the id
   */
  public Integer getId()
  {
    return _id;
  }
  /**
   * @param id the id to set
   */
  public void setId( Integer id )
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
   * @return the materno
   */
  public String getMaterno()
  {
    return _materno;
  }
  /**
   * @param materno the materno to set
   */
  public void setMaterno( String materno )
  {
    _materno = materno;
  }
  /**
   * @return the paterno
   */
  public String getPaterno()
  {
    return _paterno;
  }
  /**
   * @param paterno the paterno to set
   */
  public void setPaterno( String paterno )
  {
    _paterno = paterno;
  }
  /**
   * @return the edad
   */
  public Integer getEdad()
  {
    return edad;
  }
  /**
   * @param edad the edad to set
   */
  public void setEdad( Integer edad )
  {
    this.edad = edad;
  }
  /**
   * @return the sexo
   */
  public String getSexo()
  {
    return _sexo;
  }
  /**
   * @param sexo the sexo to set
   */
  public void setSexo( String sexo )
  {
    _sexo = sexo;
  }
  
  
  
  /**
   * @return the usuario
   */
  public UsuarioDTO getUsuario()
  {
    return _usuario;
  }
  /**
   * @param usuario the usuario to set
   */
  public void setUsuario( UsuarioDTO usuario )
  {
    _usuario = usuario;
  }
  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid()
  {
    return serialVersionUID;
  }

  
}
