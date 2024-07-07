# Multi Module Projeler için Navigation Compose Kullanımı


- Ekran route'larının tanımlı olduğu sealed class. Type-Safety özelliğinin kullanılabilmesi için ekran route'larını temsil eden `data object` ve `data class` yapılarına `@Serializable` anotasyonunun eklenmesi gerekmektedir. Böylelikle string route'lara ihtiyaç kalmadan ekran yönlendirmelerini yapmak mümkün hale gelecektir.
  
```
sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data class DetailScreen(val message: String?) : Screen()
}
```
- Navigation modülünde oluşturulan bu interface daha sonra her feature modülünde screen adıyla oluşturulacak olan NavRegisterer sınıfı tarafından extend edilecek.
```
interface NavRegisterer {
    fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController)
}
```
- Navigation modülünde oluşturulan bu data class her bir screen'i içerecek ve tip olarak NavRegisterer alacak. Bu data sınıfı sayesinde ekranlar
App modülünün içinde oluşturulacak olan NavHost tarafından erişilebilir hale gelecek.

```
data class NavProvider(
    val homeScreen : NavRegisterer,
    val detailScreen : NavRegisterer
)
```
- Feature:Home modülünün içinde oluşturulan bu sınıf NavRegisterer Interface'ini extend ederek ekranımızın navigation tarafından oluşturulmasını sağlayacak.
- Type-Safety navigation sayesinde spesifik route ve argument key'e ihtiyaç yoktur. Ekranlar string route'lar olmadan KClass şeklinde tanımlanabilir. Veri gönderilmek istendiğinde ise spesifik bir argument key'e ihtiyaç duyulmadan Data Class şeklinde tanımlanan ekranın ilgili parametresi gönderilmek istenen veri verilir.

```

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<HomeScreen> {
            HomeScreen(onNavigateDetailScreen = {navController.navigateTo(screen = DetailScreen(message = "Detail Screen Message"))})
        }
    }
}

```
- Oluşturulan bu Hilt modülünün içinde Navigation modülünün içinde oluşturulmuş olan NavProvider tanımlanacak. Böylelikle NavProvider içinde tanımlı tüm ekranlar NavHost tarafından erişilebilir hale gelecek.

```
@Module
@InstallIn(SingletonComponent::class)
object NavModule {
    @Provides
    @Singleton
    fun provideNavProvider() : NavProvider = NavProvider(
        homeScreen = HomeNavRegisterer(),
        detailScreen = DetailNavRegisterer()
    )
}

```
Ekranlara erişebilmek için oluşturulan NavGraph adlı composable fonksiyona navProvider'ı parametre olarak veriyoruz
```

@Composable
fun NavGraph(navController: NavHostController, navProvider: NavProvider) {
    NavHost(navController = navController, startDestination = Screens.DetailScreen.route) {
        navProvider.homeScreen.registerGraph(navGraphBuilder = this, navController = navController)
        navProvider.detailScreen.registerGraph(navGraphBuilder = this, navController = navController)
    }
}

```
Daha sonra Hilt modülü içinde tanımlanan NavProvider'ı MainActivity içinde Inject ederek NavGraph'ın parametresi olarak veriyoruz.

```

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navProvider: NavProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationmultimoduleprojectTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, navProvider = navProvider)
            }
        }
    }
}

```
- Type-Safety Navigation sayesinde gönderilen verileri almak oldukça kolaydır. `toRoute` fonksiyonu sayesinde veriler kolayça composable ekranımızın içinden alınır.

```

class DetailNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<DetailScreen> {
            // toRoute() fonksiyonu sayesinde navigation ile gönderilen veriyi alabiliriz
            val args = it.toRoute<DetailScreen>()
            DetailScreen(onNavigateHomeScreen = { navController.navigateTo(HomeScreen) })
        }
    }
}

```

- ViewModel içinde de gönderilen veriye ulaşılabilir. SavedStateHandle sayesinde `toRoute` fonksiyonuna ulaşılarak gönderilen veriye ulaşılır.

```


class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private var _messageState = MutableStateFlow(savedStateHandle.toRoute<Screen.DetailScreen>().message)
    val messageState: StateFlow<String?> = _messageState
    
}

```



