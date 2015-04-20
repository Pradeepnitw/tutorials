trait UserRepositoryComponent {
  val userRepository: UserRepository
  class UserRepository {
    def authenticate(user: String): String = {
      println("authenticating user: " + user)
      user
    }
    def create(user: String) = println("creating user: " + user)
    def delete(user: String) = println("deleting user: " + user)
  }
}
trait UserServiceComponent { this: UserRepositoryComponent =>
  val userService: UserService
  class UserService {
    def authenticate(username: String): String =
      userRepository.authenticate(username)
    def create(username: String, password: String) =
      userRepository.create(username)
    def delete(user: String) = userRepository.delete(user)
  }
}

object ComponentRegistry
  extends UserServiceComponent
  with UserRepositoryComponent {

  val userRepository = new UserRepository
  val userService = new UserService
}

