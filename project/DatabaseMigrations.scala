import io.ino.sbtpillar.Plugin.PillarKeys.{pillarConfigFile, pillarMigrationsDir}
import io.ino.sbtpillar.Plugin.pillarSettings
import sbt.Def.Setting
import sbt.file

object DatabaseMigrations {

  def examplePillarSettings: Seq[Setting[_]] = pillarSettings ++ overridenPillarSettings

  private[this] def overridenPillarSettings: Seq[sbt.Def.Setting[_]] = Seq(
    pillarConfigFile := file("embedded-cassandra-extension-example/src/main/resources/application.conf"),
    pillarMigrationsDir := file("embedded-cassandra-extension-example/src/main/migrations")
  )
}
