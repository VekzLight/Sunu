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

public class UsuarioDTO {
	
		private int id_usuario;
		private String nombre_usuario;
		private String email;
		private String password;
		private String rol;
		
		/**
		 * @return the id_usuario
		 */
		public int getId_usuario() {
			return id_usuario;
		}
		/**
		 * @param id_usuario the id_usuario to set
		 */
		public void setId_usuario(int id_usuario) {
			this.id_usuario = id_usuario;
		}
		/**
		 * @return the nombre_usuario
		 */
		public String getNombre_usuario() {
			return nombre_usuario;
		}
		/**
		 * @param nombre_usuario the nombre_usuario to set
		 */
		public void setNombre_usuario(String nombre_usuario) {
			this.nombre_usuario = nombre_usuario;
		}
		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return the rol
		 */
		public String getRol() {
			return rol;
		}
		/**
		 * @param rol the rol to set
		 */
		public void setRol(String rol) {
			this.rol = rol;
		}
	    
}
