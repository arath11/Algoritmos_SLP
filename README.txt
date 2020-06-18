En este documento, se busca discutir detalles, descripción y una posible solución óptima para poder resolver el problema de S-Labeling mediante un algoritmo metaheurístico. Existen algoritmos exactos y metaheurísticos, ambos son secuencias de pasos para dar con la solución hacia un problema; sin embargo, tienen sus diferencias. Los exactos son todos aquellos algoritmos que dan con la respuesta con una eficiencia del 100%, es decir, dan con el resultado correcto para cada uno de sus casos, al contrario que los metaheurísticos, los cuales no dan con la solución en todos ellos, aunque son de gran utilidad para resolver problemas los cuales involucran entradas muy grandes. Un ejemplo podría ser un grafo que contenga más allá de un 1,000,000 de nodos; un algoritmo exacto tiene la desventaja de tener que comparar cada uno de los nodos y en comparación a los metaheurísticos, estos no comparan todos y solo toma una muestra.







Definición formal del problema

El problema de S-Labeling consiste en lo siguiente: dado un un grafo G=(V,E), de orden n y grado máximo ?, donde V es el número de nodos del grafo y E el número de aristas, encontrar un etiquetado que se define con un mapeo biyectivo
f : V ? {1, 2 ...n}, tal que SLf(G) =  ?{u, v} ? E min{f(u), f(v)} sea minimizado.
Ejemplo para ilustrar el problema combinatorio analizado















Descripción detallada y pseudocódigo

Por la naturaleza del problema se determinó que la forma óptima para solucionarlo es a través de un algoritmo memético. Para el entendimiento de este, se deben comprender el término de algoritmia como de tipo evolutivo y genético. 
2.1 Algoritmo Evolutivo
Los algoritmos evolutivos son un tipo de solución estocástica (no determinista) de aproximación de soluciones, los cuales operan sobre poblaciones realizando una búsqueda paralela implícita de las mejores soluciones. Sus operadores de variación (combinación y permutación) son el mecanismo que explora y explota los espacios. El mecanismo de selección está directamente relacionado con qué  tan bien o mal se resuelve el problema con respecto a la calidad de la solución. En otras palabras, el conjunto de posibles soluciones, evolucionan a mejores soluciones.
2.1.1 Representación
Se deben representar las posibles soluciones en forma de individuos.
	Población: Es un conjunto finito de individuos. Su cardinalidad está acotada (usualmente es de tamaño fijo). En este caso, se habla de las permutaciones de n nodos. La naturaleza del algoritmo es comenzar con una población aleatoria. 
Función de evaluación (aptitud) de individuos: En optimización, la aptitud de cada individuo se relaciona con la función objetivo (función de utilidad). En este caso, se trata de encontrar  SLf(G) =  ?{u, v} ? E min{f(u), f(v)}

       Representación gráfica del algoritmo evolutivo
Selección de Padres


Cada individuo tiene asignado una cierta probabilidad de reproducción, dependiendo su aptitud. La selección es probabilística, es decir, las soluciones de mayor calidad tienen mayor probabilidad de reproducción.
Operación de Variación 
Su razón de ser es generar nuevas soluciones a cada generación. Toman en cuenta el número de individuos involucrados (mutación, cruza, recombinación, etc). Para este algoritmo, la solución eficaz es mediante el uso de cruza.
Cruza
Mezcla la información genética de los padres para reproducir descendientes.
La decisión de qué información se toma de cada quien es estocástica.
Se tiene la esperanza de que algunos de los descendientes sean mejores que sus padres.
Representación de cruza
Mutación
La mutación afecta a un individuo de la población. Se puede sustituir un nodo entero en el individuo seleccionado, o puede simplemente reemplazar la información del nodo. Para mantener la integridad, las operaciones deben ser salvo fallos o el tipo de información que el nodo tiene debe ser tomada en cuenta. Por ejemplo, la mutación debe ser consciente de nodos operación binaria, o el operador debe ser capaz de manejar los valores que faltan.
2.2 Algoritmo genético
Los algoritmos genéticos corresponden a una metaheurística de los algoritmos evolutivos. Es una de las metaheurísticas más utilizadas y funciona con todas las bases evolutivas que se explicaron con anterioridad. A continuación se presenta un ejemplo de algoritmo genético con operación de variación de cruza. Más adelante, en los algoritmos meméticos, se explica la finalidad de este algoritmo.

Representación de un algoritmo genético simple (Moujahid, Inza, Larrañaga, s.f.)
2.3 Algoritmo memético 
Es un algoritmo que combina un método poblacional (global) con un método local, el cual involucra información acerca del problema (funciones, restricciones, espacios de búsqueda). La idea es que cada técnica subsane las deficiencias de la otra. Combinan sinérgicamente conceptos tomados de otras metaheurísticas, tales como la búsqueda basada en poblaciones (como en los algoritmos evolutivos), y la mejora local (como en las técnicas de seguimiento del gradiente). 
Implementación memética

	Ejemplo de un algoritmo memético básico simple (Neri, Moscato, Cotta, 2012)

2.3.1 Creación de la población inicial
Como observamos en el pseudocódigo anterior, el primer paso del algoritmo memético es la creación de  población inicial. Las cualidades de una población son muy simples para este algoritmo: definimos poblaciones de 50 individuos cada una. A su vez, las cualidades de un individuo se definen por: orden de etiquetación y suma mínima para esa permutación, esto lo representamos a través de objetos de tipo Individuo. 
Tomando esto en cuenta, podemos iniciar la primer población creando una primera permutación de individuos random. Esta población posteriormente después de crearse pasará a evaluarse a través de la función Torneo Binario.

2.3.1.1 Sumas mínimas


El algoritmo, recibe un Arraylist que contiene la permutación (al individuo), entra dentro de un for loop , el cual recorre nuestra estructura de datos para identificar las etiquetas que se van a comparar de la permutación. Posteriormente, las etiquetas mínimas son acumuladas dentro de un acumulador, para poder regresar después de la ejecución, la suma total de las etiquetas con base al grafo establecido en la estructura (Triplet Representation).




2.3.2 Torneo Binario
El método torneo binario, selecciona 4 individuos de la población al azar, de los cuales se hacen dos torneos, donde competirán dos contra dos; posteriormente, los dos ganadores del torneo se cruzaran para tener dos hijos hasta tener una población nueva.


2.3.2.1 Cruza (Implementación genética)
El método cruza corresponde a la definición que se mencionó en apartados anteriores, utilizando las librerías incluidas en Opt4J.Se toma a dos individuos, los cuales provienen del torneo binario (sus ganadores), para cruzarlos y generar dos hijos. Los hijos serán añadidos a la población total de hijos.

2.3.2.2 Hijos Elegidos
La función hijosElegidos, selecciona al azar de la población total de hijos, el número de individuos  igual al tamaño de la población original, obteniendo una población de hijos reducida.

2.3.2.3 Mutación
En  mutación se recorre toda la población de supervivientes para mutar  cada uno de los individuos, obteniendo de igual manera su permutación y etiquetado mínimo.  
    
2.3.2.4 Búsqueda Local
El propósito de la función búsqueda local es para buscar los vecinos de una permutación haciendo swaps y los guarda en caso de que disminuyera el valor de su etiqueta, al disminuir el costo de la etiqueta podemos repetirlo e incluso encontrar una etiqueta aún menor y así descubrimos nuevos posibles caminos para llegar a la solución menor. 

2.3.2.5 Selección Superviviente
En la selección final se hace una unión de la población original y de la población superviviente la cual ya pasó por una etapa de torneo binario, cruza, selección de supervivientes, mutación y una búsqueda local, para posteriormente sacar el individuo con  el etiquetado más pequeño. Después se genera la población de la siguiente generación, la cual está compuesta por los mejores individuos de la población total a excepción de 5 individuos, los cuales serán tomados de la población total de manera aleatoria.



