class JobFactory {
    public Job createJobWithInitialVacancies(String... initialVacancies) {
        Job job = new Job();
        for (String vacancy : initialVacancies) {
            job.addVacancy(vacancy);
        }
        return job;
    }
}
