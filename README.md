# MEET APP

Empoderamento dos pacientes de tumores durante o seu tratamento

## DESCRICIÓN DO ANTEPROXECTO

### Introdución

Na detección dun cancro, a velocidade de resposta do proceso médico para a supervivencia do paciente é crucial. O proceso comeza ca sospeita dun médico de que o paciente pode padecer dita enfermidade. Realízanse unha serie de probas ao paciente, e no caso de ser positivas, un comité de expertos reúnese para ditaminar un tratamento. En cada momento do proceso haberá un experto asignado ao paciente o cal encargarase do seu seguimento.

En moitos casos, debido a que os pacientes esquecen as citas que teñen, ou non van preparados para as mesmas (non van xaxuados), os procesos que levan a detección e tratamento dos tumores son atrasados con respecto ás pautas establecidas. Complicando a efectividade dos mesmos.

Actualmente séguese unha corrente de empoderamento do paciente. Xa que sendo el a peza central, é vital que dispoña da maior información útil posible sobre a enfermidade e o proceso que se está a levar a cabo.

O empoderamento do paciente, consiste en dar ao paciente a capacidade de decidir e ter control sobre a súa vida e o seu tratamento. Isto é a contraposición do sistema anterior, no cal os expertos encargábanse de todo, sen ter en conta ao paciente á hora de tomar decisións.

No pasado, un médico non daba toda a información a un paciente, tratando tamén de suavizar a visión do doente cara a súa enfermidade. A tendencia actual é a oposta. É a de dar toda a información que poida resultar útil para o paciente. Ben sexa sobre a enfermidade, sobre o seu estado actual, sobre cal é o proceso que seguiu e seguirá para a súa recuperación...Todo isto a través dunha conexión de información constante entre o experto encargado do paciente e este.

### Obxectivos

O obxectivo principal deste proxecto é a creación dunha aplicación móbil nativa do sistema Android, para poder realizar dun xeito sinxelo e satisfactorio o empoderamento do paciente. É dicir, buscarase que o paciente vexa que o feedback que deixa na aplicación, a información que recibe (tanto avisos, como información médica), dean ao paciente un papel totalmente central dentro da súa doenza. Para isto, será necesario cumprir os seguintes subobxectivos:
1. O paciente poderá visualizar a súa ficha en calquera momento, accedendo a esta información dende o seu terminal móbil a través da aplicación desenvolta.
2. A aplicación ten que ser unha canle de intercambio de información entre o paciente e o profesional que ten asignado. Para isto, os expertos en todo momento poderán compartir información co paciente sobre a saúde do mesmo, ou información xeral sobre a enfermidade, que eles vexan relevante para o paciente. Tamén disporase dun chat para enviar mensaxes entre o paciente e o experto.
3. A aplicación mostrará notificacións e alertas ao paciente para mostrar que chegarán dende un sistema centralizado e xa desenvolto dentro da empresa colaboradora.
4. A aplicación solicitará ao paciente que rexistre feedback sobre o estado de saúde e o seguimento do proceso durante toda a duración do mesmo.

### Descrición técnica

Esta aplicación comunicará aos pacientes/coidadores, cos profesionais encargados destes da sanidade para o intercambio de información durante o proceso de tratamento do paciente. A aplicación funcionará de xeito simultáneo cun sistema para xestión de comités de tumores, que será o encargado de xerar as notificacións da aplicación. A aplicación entón será unha ponte de conexión entre o sistema e os usuarios da aplicación, sendo pacientes ou expertos.

![App interaction](https://github.com/AlejandroFraga/meet-app/blob/main/images/interaction.png?raw=true)

A fase de análise centrarase na comprensión de como o paciente poderá visualizar a súa ficha, con información resumida enfocada a este. De este modo, o paciente podería acceder a un subconxunto da información relacionada cos seus problemas de saúde, asegurando que dispón de toda a información necesaria.

Estudarase a información do paciente a mostrar, a información que poderá ser mandada polos expertos, para buscar o xeito máis cómodo e simple de representala e enviala. Tamén revisarase o funcionamento actual do sistema ao cal se incorporará a aplicación para que esta estea en concordancia e funcione correctamente ca interface de comunicación definida entre elas. A información a mostrar dos pacientes será a seguinte:
 * Citacións.
 * Preparativos necesarios para probas diagnósticas.
 * Pautas para tratamentos.
 * Información sobre a súa doenza.

Estudaranse as diferentes notificacións, que estarán enfocadas a mostrar:
 * Citación: data, hora, lugar, preparativos necesarios...
 * Indicacións para o tratamento.
 
Estudaremos como realizar as comunicacións do chat entre os pacientes e os expertos. Buscarase como conseguir un feedback útil e simple para os expertos, a través de preguntas concisas e adoptadas actualmente á hora de observar a evolución dun paciente das mesmas características.

Na fase de deseño, como podemos ver no simple esquema da situación da aplicación no seu entorno, a aplicación pasará a funcionar en conxunto co sistema para xestión dos comités de tumores. O cal, non será parte deste proxecto e xa está actualmente en funcionamento. Este sistema terá unha interface de comunicacións xa definida ca aplicación, tanto para as notificacións, como para o intercambio de información de cada un dos pacientes ou dos profesionais.

Buscaremos que a aplicación resulte atractiva ao seu uso por parte dos pacientes. Cunha funcionalidade e deseño gráfico simples, intuitivos e visuais. Para isto utilizaremos técnicas de gamificación. Estas, mostráronse moi útiles para incrementar a motivación e compromiso dos pacientes no coidado da súa saúde.

Deseñaremos como se mostrará a información do paciente. Tamén o funcionamento do chat, que estará controlado pola aplicación, a cal se encargará de transmitir e recibir as mensaxes dos pacientes aos expertos, e viceversa.

Dentro do desenvolvemento, a aplicación crearase utilizando a IDE oficial para Android: Android Studio, e a IDE de programación Netbeans para certas rexións do código Java, máis enfocadas á interacción co sistema ao que se incorporará a aplicación, que á aplicación en si.

Utilizaranse as linguaxes de programación:
 * Java: para a maior parte de funcionalidades da aplicación.
 * Kotlin: sendo unha linguaxe oficial open source de Android con total interoperabilidade con Java.

### Medios materiais necesarios.
 * Ordenador persoal
 * Dispositivo móbil Android
 * Android Studio (IDE oficial para Android)
 * Netbeans (IDE)
 * Micosoft Office Word 2016
 * Google Slides

## FASES DO TRABALLO E ESTIMACIÓN TEMPORAL
Un traballo de fin de grao suporá 401,25 horas de traballo autónomo do alumnado e 11,25 horas de traballo presencial (titorías e avaliación).
Dedicación semanal prevista (en horas/semana): 27,5

|Fase|Estimación temporal (en semanas)|
|---|---|
|Xestión do proxecto|1|
|Inicialización|3,5|
|Deseño|3,5|
|Desarrollo|6|
|Probas|2
|Finalización|1|

(Para a xestión do alcance do proxecto deberase incluír en tódolos casos unha EDT segundo se recolle no PMBOK do PMI. Capítulo 5, Xestión do Alcance do Proxecto)

![Work Breakdown Structure](https://github.com/AlejandroFraga/meet-app/blob/main/images/wbs.png?raw=true)