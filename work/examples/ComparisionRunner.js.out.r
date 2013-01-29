jpeg("ComparisionRunner.js.out.jpg")
data <- c(0.8680981972739265, 0.6986522446861213, 0.9458754128100468, 0.9328905423113754)
name <- c("Master-Slave", "Random", "Round Robin", "Weighted RR")
barplot(data, names.arg = name, main = "balancing efficiency")
dev.off()
q()
      