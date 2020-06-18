En este documento, se busca discutir detalles, descripci�n y una posible soluci�n �ptima para poder resolver el problema de S-Labeling mediante un algoritmo metaheur�stico. Existen algoritmos exactos y metaheur�sticos, ambos son secuencias de pasos para dar con la soluci�n hacia un problema; sin embargo, tienen sus diferencias. Los exactos son todos aquellos algoritmos que dan con la respuesta con una eficiencia del 100%, es decir, dan con el resultado correcto para cada uno de sus casos, al contrario que los metaheur�sticos, los cuales no dan con la soluci�n en todos ellos, aunque son de gran utilidad para resolver problemas los cuales involucran entradas muy grandes. Un ejemplo podr�a ser un grafo que contenga m�s all� de un 1,000,000 de nodos; un algoritmo exacto tiene la desventaja de tener que comparar cada uno de los nodos y en comparaci�n a los metaheur�sticos, estos no comparan todos y solo toma una muestra.







Definici�n formal del problema

El problema de S-Labeling consiste en lo siguiente: dado un un grafo G=(V,E), de orden n y grado m�ximo ?, donde V es el n�mero de nodos del grafo y E el n�mero de aristas, encontrar un etiquetado que se define con un mapeo biyectivo
f : V ? {1, 2 ...n}, tal que SLf(G) =  ?{u, v} ? E min{f(u), f(v)} sea minimizado.
Ejemplo para ilustrar el problema combinatorio analizado















Descripci�n detallada y pseudoc�digo

Por la naturaleza del problema se determin� que la forma �ptima para solucionarlo es a trav�s de un algoritmo mem�tico. Para el entendimiento de este, se deben comprender el t�rmino de algoritmia como de tipo evolutivo y gen�tico. 
2.1 Algoritmo Evolutivo
Los algoritmos evolutivos son un tipo de soluci�n estoc�stica (no determinista) de aproximaci�n de soluciones, los cuales operan sobre poblaciones realizando una b�squeda paralela impl�cita de las mejores soluciones. Sus operadores de variaci�n (combinaci�n y permutaci�n) son el mecanismo que explora y explota los espacios. El mecanismo de selecci�n est� directamente relacionado con qu�  tan bien o mal se resuelve el problema con respecto a la calidad de la soluci�n. En otras palabras, el conjunto de posibles soluciones, evolucionan a mejores soluciones.
2.1.1 Representaci�n
Se deben representar las posibles soluciones en forma de individuos.
	Poblaci�n: Es un conjunto finito de individuos. Su cardinalidad est� acotada (usualmente es de tama�o fijo). En este caso, se habla de las permutaciones de n nodos. La naturaleza del algoritmo es comenzar con una poblaci�n aleatoria. 
Funci�n de evaluaci�n (aptitud) de individuos: En optimizaci�n, la aptitud de cada individuo se relaciona con la funci�n objetivo (funci�n de utilidad). En este caso, se trata de encontrar  SLf(G) =  ?{u, v} ? E min{f(u), f(v)}

       Representaci�n gr�fica del algoritmo evolutivo
Selecci�n de Padres


Cada individuo tiene asignado una cierta probabilidad de reproducci�n, dependiendo su aptitud. La selecci�n es probabil�stica, es decir, las soluciones de mayor calidad tienen mayor probabilidad de reproducci�n.
Operaci�n de Variaci�n 
Su raz�n de ser es generar nuevas soluciones a cada generaci�n. Toman en cuenta el n�mero de individuos involucrados (mutaci�n, cruza, recombinaci�n, etc). Para este algoritmo, la soluci�n eficaz es mediante el uso de cruza.
Cruza
Mezcla la informaci�n gen�tica de los padres para reproducir descendientes.
La decisi�n de qu� informaci�n se toma de cada quien es estoc�stica.
Se tiene la esperanza de que algunos de los descendientes sean mejores que sus padres.
Representaci�n de cruza
Mutaci�n
La mutaci�n afecta a un individuo de la poblaci�n. Se puede sustituir un nodo entero en el individuo seleccionado, o puede simplemente reemplazar la informaci�n del nodo. Para mantener la integridad, las operaciones deben ser salvo fallos o el tipo de informaci�n que el nodo tiene debe ser tomada en cuenta. Por ejemplo, la mutaci�n debe ser consciente de nodos operaci�n binaria, o el operador debe ser capaz de manejar los valores que faltan.
2.2 Algoritmo gen�tico
Los algoritmos gen�ticos corresponden a una metaheur�stica de los algoritmos evolutivos. Es una de las metaheur�sticas m�s utilizadas y funciona con todas las bases evolutivas que se explicaron con anterioridad. A continuaci�n se presenta un ejemplo de algoritmo gen�tico con operaci�n de variaci�n de cruza. M�s adelante, en los algoritmos mem�ticos, se explica la finalidad de este algoritmo.

Representaci�n de un algoritmo gen�tico simple (Moujahid, Inza, Larra�aga, s.f.)
2.3 Algoritmo mem�tico 
Es un algoritmo que combina un m�todo poblacional (global) con un m�todo local, el cual involucra informaci�n acerca del problema (funciones, restricciones, espacios de b�squeda). La idea es que cada t�cnica subsane las deficiencias de la otra. Combinan sin�rgicamente conceptos tomados de otras metaheur�sticas, tales como la b�squeda basada en poblaciones (como en los algoritmos evolutivos), y la mejora local (como en las t�cnicas de seguimiento del gradiente). 
Implementaci�n mem�tica

	Ejemplo de un algoritmo mem�tico b�sico simple (Neri, Moscato, Cotta, 2012)

2.3.1 Creaci�n de la poblaci�n inicial
Como observamos en el pseudoc�digo anterior, el primer paso del algoritmo mem�tico es la creaci�n de  poblaci�n inicial. Las cualidades de una poblaci�n son muy simples para este algoritmo: definimos poblaciones de 50 individuos cada una. A su vez, las cualidades de un individuo se definen por: orden de etiquetaci�n y suma m�nima para esa permutaci�n, esto lo representamos a trav�s de objetos de tipo Individuo. 
Tomando esto en cuenta, podemos iniciar la primer poblaci�n creando una primera permutaci�n de individuos random. Esta poblaci�n posteriormente despu�s de crearse pasar� a evaluarse a trav�s de la funci�n Torneo Binario.

2.3.1.1 Sumas m�nimas


El algoritmo, recibe un Arraylist que contiene la permutaci�n (al individuo), entra dentro de un for loop , el cual recorre nuestra estructura de datos para identificar las etiquetas que se van a comparar de la permutaci�n. Posteriormente, las etiquetas m�nimas son acumuladas dentro de un acumulador, para poder regresar despu�s de la ejecuci�n, la suma total de las etiquetas con base al grafo establecido en la estructura (Triplet Representation).




2.3.2 Torneo Binario
El m�todo torneo binario, selecciona 4 individuos de la poblaci�n al azar, de los cuales se hacen dos torneos, donde competir�n dos contra dos; posteriormente, los dos ganadores del torneo se cruzaran para tener dos hijos hasta tener una poblaci�n nueva.


2.3.2.1 Cruza (Implementaci�n gen�tica)
El m�todo cruza corresponde a la definici�n que se mencion� en apartados anteriores, utilizando las librer�as incluidas en Opt4J.Se toma a dos individuos, los cuales provienen del torneo binario (sus ganadores), para cruzarlos y generar dos hijos. Los hijos ser�n a�adidos a la poblaci�n total de hijos.

2.3.2.2 Hijos Elegidos
La funci�n hijosElegidos, selecciona al azar de la poblaci�n total de hijos, el n�mero de individuos  igual al tama�o de la poblaci�n original, obteniendo una poblaci�n de hijos reducida.

2.3.2.3 Mutaci�n
En  mutaci�n se recorre toda la poblaci�n de supervivientes para mutar  cada uno de los individuos, obteniendo de igual manera su permutaci�n y etiquetado m�nimo.  
    
2.3.2.4 B�squeda Local
El prop�sito de la funci�n b�squeda local es para buscar los vecinos de una permutaci�n haciendo swaps y los guarda en caso de que disminuyera el valor de su etiqueta, al disminuir el costo de la etiqueta podemos repetirlo e incluso encontrar una etiqueta a�n menor y as� descubrimos nuevos posibles caminos para llegar a la soluci�n menor. 

2.3.2.5 Selecci�n Superviviente
En la selecci�n final se hace una uni�n de la poblaci�n original y de la poblaci�n superviviente la cual ya pas� por una etapa de torneo binario, cruza, selecci�n de supervivientes, mutaci�n y una b�squeda local, para posteriormente sacar el individuo con  el etiquetado m�s peque�o. Despu�s se genera la poblaci�n de la siguiente generaci�n, la cual est� compuesta por los mejores individuos de la poblaci�n total a excepci�n de 5 individuos, los cuales ser�n tomados de la poblaci�n total de manera aleatoria.



