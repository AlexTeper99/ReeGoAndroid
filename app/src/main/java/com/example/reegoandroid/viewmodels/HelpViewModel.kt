package com.example.reegoandroid.viewmodels

import androidx.lifecycle.ViewModel

class HelpViewModel : ViewModel() {

   var titleText = "Bienvenido a Reego"
   //var textHelp = "Bienvenido a la aplicacion Reego. Muchas gracias por confiar en nosotros para mantener sus cultivos hidratados y a salvo. En caso de querer recibir soporte para su sistema y/o iniciar en este mundo del riego automatizado por favor contactenos a soporte@reego.com "
   var textHelp = """Bienvenido a Reego. Reego es un sistema de soluciones para todas las necesidades de riego que puedas tener. Ofrecemos un sistema de riego autónomo especialmente adaptado para multiples tipos de cultivos, que te va a brindar mayores cosechas con menos trabajo. 
      |              Esto lo logramos en base a un algoritmo que toma en cuenta tanto factores meteorológicos previstos como en tiempo real de tu campo, para mantener la humedad ideal en tus cultivos. Para comenzar, solo necesitamos que nos brindes un poco de información sobre tus cultivos y el lugar donde se encuentran. 
      |              Queremos que puedas dedicarte a lo que realmente te gusta, y nosotros nos encargamos de mantener tus cultivos en las mejores condiciones. Para implementar Reego en tu campo envianos un mail con tus datos a ventas@reego.com 
      |              Si ya sos usuario y no podes ingresar a tu cuenta escribinos a soporte@reego.com 
      |              ¡Comienza a regar inteligentemente!""".trimMargin()
}