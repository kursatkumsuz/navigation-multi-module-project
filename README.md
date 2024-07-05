# Multi Module Projeler için Navigation Compose Kullanımı


- Ekran route'larının tanımlı olduğu sealed class
```
sealed class Screens(val route : String) {
    data object HomeScreen : Screens("home_screen")
    data object DetailScreen : Screens("detail_screen")
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

```

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(Screens.HomeScreen.route) {
            HomeScreen(onNavigateDetailScreen = {navController.navigate(route = Screens.DetailScreen.route)})
        }
    }
}
```
Oluşturulan bu Hilt modülünün içinde Navigation modülünün içinde oluşturulmuş olan NavProvider tanımlanacak. Böylelikle NavProvider içinde tanımlı tüm ekranlar NavHost tarafından
erişilebilir hale gelecek.

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
