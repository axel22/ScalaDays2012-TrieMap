import sbt._
import Keys._
import Process._
import java.io.File



object ScalapoolBuild extends Build {
  
  /* tasks and settings */
  
  val javaCommand = TaskKey[String](
    "java-command",
    "Creates a java vm command for launching a process."
  )
  val javaCommandSetting = javaCommand <<= (
    dependencyClasspath in Compile,
    artifactPath in (Compile, packageBin),
    artifactPath in (Test, packageBin),
    packageBin in Compile,
    packageBin in Test
  ) map {
    (dp, jar, testjar, pbc, pbt) => // -XX:+UseConcMarkSweepGC  -XX:-DoEscapeAnalysis -XX:MaxTenuringThreshold=12 -XX:+UseCondCardMark 
    val javacommand = "java -Dsun.java2d.opengl=True -Xmx1512m -Xms1512m -server -cp %s:%s:%s".format(
      dp.map(_.data).mkString(":"),
      jar,
      testjar
    )
    javacommand
  }
  
  val benchTask = InputKey[Unit](
    "bench",
    "Runs a specified benchmark."
  ) <<= inputTask {
    (argTask: TaskKey[Seq[String]]) =>
    (argTask, javaCommand) map {
      (args, jc) =>
      val javacommand = jc
      val comm = javacommand + " " + args.mkString(" ")
      println("Executing: " + comm)
      comm!
    }
  }
  
  val classpath = TaskKey[String](
    "classpath-flag",
    "The complete classpath flag."
  )
  val classpathSetting = classpath <<= (
    dependencyClasspath in Compile,
    artifactPath in (Compile, packageBin),
    artifactPath in (Test, packageBin),
    packageBin in Compile,
    packageBin in Test
  ) map {
    (dp, jar, testjar, pbc, pbt) =>
    val javacommand = "-cp %s:%s:%s".format(
      dp.map(_.data).mkString(":"),
      jar,
      testjar
    )
    javacommand
  }
  
  /* projects */
  
  lazy val jvmbench = Project(
    "scaladays-triemap",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(benchTask, javaCommandSetting, classpathSetting)
  ) dependsOn (
  )
  
}
