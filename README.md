# YapeChallenge

##  **** ACLARACIÓN ****
	Para el correcto funcionamiento del mapa, se debe agregar la clave api en el archico "local.properties" en la sección de "Gradle Scripts" 
	reemplazando el valor "YOUR_API_KEY" por el valor de su clave api. Ej: GOOGLE_MAPS_API_KEY = azJhtYoijgR3JhlHp7

1- Librerias.
	a- Las librerias utilizadas fueron agregadas ya que sin las mismas afectaría al funcionamiento de la app.

	b- Para las imágenes utilizé Picasso, ya que considero que es una libreria confiable y muy conocida.

	c- Para las peticiones a la API utilicé Retrofit que es la libreria que se utiliza actualmente y es la recomendada.

	d- Para la base de datos utilicé Room ya que la misma es muy potente y te brinda una abstracción para SQLite.

	e- Se utilizó Navigation view para la navegación entre las diferentes pantallas de la aplicación.

2- Arquitectura.
	La arquitectura que se utilizó fué MVVM(model view view model) que es la arquitectura recomendada por google y es una de las
	mas utilizadas hoy en dia. La misma se agregó al proyecto ya que para el desarrollo, orden y la escalabilidad del mismo es impescindible seguir
	una arquitectura.
	Esto conlleva tener una arquitectura clean en el proyecto.

3- Patrones.
	Respecto a los patrones, los mismos fueron utilizados en el proyecto con el objetivo de optimizar el código.

4- Pruebas automatizadas.
	Se crearon Unit Test a la funcionalidades del caso de uso y del view model con el fin de controlar que se ejecute el flujo correspondiente.

5- Novedades.
	Hay ciertas cosas como herramientas y funciones que no eran requeridas, pero las mismas fueron agregadas ya que considero que son muy buena práctica
	implementarlas y además ayudan al desarrollo del día a día.

		1- Una de ellas fue la implementación de inyección de dependencias con la libreria dagger hilt, ya que esto te permite agilizar mucho el proceso
		a la hora de crear nuevas instancias en los diferentes módulos a la vez que se reduce el código y lo hace mas legible.

		2- Se implementó una base de datos con Room para que la aplicación pueda seguir utilizándose si el usuario en algún momento no cuenta con 
		conexión a internet, ya que los datos quedan guardados localmente en el dispositivo. Por ese motivo decidí incorporar una base de datos local y
		un flujo alternativo cuando se solicitan los datos.
