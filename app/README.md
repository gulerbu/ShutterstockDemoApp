
ABOUT DEMOAPP
------------------------

DemoApp is an Android mobile application which fetches images from Shutterstock API and show them with their descriptions in a infinite scrollable view.

Project is developed in MVVM and Clean architecture principles, as it enhances separation of concerns and allows keeping ui simple and free of app logic,
makes maintaining- testing easier. SOLID principles are taken into consideration in development. Android Architecture Components is used as it works well with MVVM.
Basically the application consist of View, View Model, Use Case,Data Source and Repository. Application is developed only with activity as it has a simple one screen.

- Retrofit is used for network calls. Kotlin coroutines are supported by Retrofit, so requests are made by coroutines with clear and linear code. RXJava is not preferred
as it will be used only for network calls and there is nothing based in reactive stream in the architecture. It is native Kotlin application so there is no use to add external library
for operations coroutines can handle. Also Kotlin coroutines are very lightweight and efficient and they can interact with RXJava when necessary.

- Image loading is succeed with Picasso.

- Koin is used dependency inversion. The reason why service locator pattern is preferred that the application is a small sized (1 screen) and as the
application does not seem to grow. But if this was a large application it would be better to use Dagger for this purpose.


- LiveData is used for observing UI changes as it can work well with MVVM.

- DataBinding is used to bind UI elements.

- Mockito and Expresso are used for testing.

- Shutterstock api preview image is used for images as larger images take too long to download. If there will be any detail page higher resolution images can be used in detail page.

WHAT IS LEFT OUT/ WHAT COULD BE BETTER IMPLEMENTED
---------------------------------------------------

- Swipe to refresh is not implemented for this version. Right now when error occurs users see full screen error message and retry button. Retry button makes service call
from first page. It is better and more efficient to implement swipe to refresh and send request for  only the necessary page.

- Android Paging Library could be used for infinite scrolling

- Generally, network responses are stubbed with mockito because there is only one call. But if there is more than one call or repository actions it is better to implement fake data
source for testing purposes.

- UI and unit testing coverage is limited. They should cover the all possible cases. DataBindingIdlingResource is implemented because Espresso does not wait for databinding updates.
However it is not working as it supposed to be, so Thread.sleep() is used in instrumentation test even though it needs to be avoided.

- BaseRepository can be implemented and response handling can be handled in base.

- State handling is implemented in activity. It can be delegated to another class like StateHandler and can be handled in base classes.

LinkedIn profile: https://www.linkedin.com/in/burcu-guler/

Source code link: https://github.com/gulerbu/ShutterstockDemoApp

