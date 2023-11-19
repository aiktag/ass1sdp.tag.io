abstract class HeroDecorator implements Hero {
    protected Hero decoratedHero;

    public HeroDecorator(Hero decoratedHero) {
        this.decoratedHero = decoratedHero;
    }

    public void equip() {
        decoratedHero.equip();
    }
}