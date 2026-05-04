@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public String register(
            @RequestBody User user){

        service.register(user);

        return "registered";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody User user){

        return service.login(user);
    }
}
